package com.gestion_restaurant.gestion_restaurant.dto;

public record ClientDTOResponse(
        Long id,
        String nom,
        String prenom,
        String telephone,
        String localisation
) {
}
