package com.gestion_restaurant.gestion_restaurant.DTO;

public record Article_MenuDtoResponse(
        Long id,
        String nom_plat,
        String description,
        double prix
) {
}
