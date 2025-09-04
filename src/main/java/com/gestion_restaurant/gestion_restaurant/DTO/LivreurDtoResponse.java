package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;

public record LivreurDtoResponse(
        String nom,
        String prenom,
        String phoneNumber,
        StatutLivreur status
) {
}
