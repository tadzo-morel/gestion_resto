package com.gestion_restaurant.gestion_restaurant.security.controller;

import com.gestion_restaurant.gestion_restaurant.security.dto.LoginRequest;
import com.gestion_restaurant.gestion_restaurant.security.dto.LoginResponse;
import com.gestion_restaurant.gestion_restaurant.security.dto.RegisterRequest;
import com.gestion_restaurant.gestion_restaurant.security.dto.UserInfoDto;
import com.gestion_restaurant.gestion_restaurant.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserInfoDto> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    
    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
}