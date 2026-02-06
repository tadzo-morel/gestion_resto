package com.gestion_restaurant.gestion_restaurant.DTO;

public record AuthResponseDto(
        String token,
        String type,
        Long userId,
        String nom,
        String email,
        String role,
        Long expiresIn
) {
    public AuthResponseDto(String token, Long userId, String nom, String email, String role) {
        this(token, "Bearer", userId, nom, email, role, 3600L);
    }
}