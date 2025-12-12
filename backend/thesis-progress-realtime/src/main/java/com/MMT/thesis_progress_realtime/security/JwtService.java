package com.MMT.thesis_progress_realtime.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final byte[] secretBytes;
    private final long accessTokenMinutes;

    public JwtService(
        @Value("${app.jwt.secret}") String secret,
        @Value("${app.jwt.accessTokenMinutes}") long minutes
    ) {
        this.secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.accessTokenMinutes = minutes;
    }

    public String generateToken(Long userId, String email, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessTokenMinutes * 60);

        return Jwts.builder() 
            .subject(String.valueOf(userId))
            .claim("email", email)
            .claim("roles", roles)
            .issuedAt(Date.from(now))
            .expiration(Date.from(exp))
            .signWith(Keys.hmacShaKeyFor(secretBytes))
            .compact();
    }

    public io.jsonwebtoken.Claims parseClaims(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secretBytes))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
