package com.example.authservice.controller;

import com.example.authservice.dto.LoginRequest;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class AuthController {

    private final JwtEncoder jwtEncoder;

    public AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @GetMapping("/auth/test")
    public String test() {
        return "Auth Service Working";
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(loginRequest.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

        return "Login Request Received";
    }
}