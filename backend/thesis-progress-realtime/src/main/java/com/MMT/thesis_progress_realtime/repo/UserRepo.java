package com.MMT.thesis_progress_realtime.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MMT.thesis_progress_realtime.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
