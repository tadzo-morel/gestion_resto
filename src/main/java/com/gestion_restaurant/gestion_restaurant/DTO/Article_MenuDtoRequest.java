package com.gestion_restaurant.gestion_restaurant.dto;

public record Article_MenuDtoRequest(
        String nom_plat,
        String description,
        double prix
) {
}
