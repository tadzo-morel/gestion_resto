package com.gestion_restaurant.gestion_restaurant.security.dto;

public record UserInfoDto(
    Long id,
    String nom,
    String prenom,
    String email,
    String telephone,
    String role,
    String userType
) {}