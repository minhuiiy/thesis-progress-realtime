package com.MMT.thesis_progress_realtime.api;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.MMT.thesis_progress_realtime.repo.UserRepo;
import com.MMT.thesis_progress_realtime.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepo userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    public record LoginReq(@NotBlank String email, @NotBlank String password) {}

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginReq req) {
        var user = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (!encoder.matches(req.password(), user.getPasswordHash())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }

        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName())
                .toList();

        String token = jwtService.generateToken(user.getId(), user.getEmail(), roles);

        return Map.of(
                "accessToken", token,
                "userId", user.getId(),
                "email", user.getEmail(),
                "roles", roles
        );
    }
}
