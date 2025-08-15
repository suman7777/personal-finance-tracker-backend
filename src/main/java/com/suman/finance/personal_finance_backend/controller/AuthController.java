package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.UserEntity;
import com.suman.finance.personal_finance_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginReq) {
        UserEntity user = userRepository.findByUsername(loginReq.getUsername());
        if (user != null && user.getPassword().equals(loginReq.getPassword())) {
            // In production, use hashed passwords and JWT/session
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
