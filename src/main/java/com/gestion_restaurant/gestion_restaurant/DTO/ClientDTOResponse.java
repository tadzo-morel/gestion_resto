package com.gestion_restaurant.gestion_restaurant.DTO;

public record ClientDTOResponse(
        String nom,
        String prenom,
        String phoneNumber,
        String localisation
) {
}
