package com.gestion_restaurant.gestion_restaurant.DTO;

public record ClientDTOResponse(
        Long id,
        String nom,
        String prenom,
        String telephone,
        String localisation
) {
}
