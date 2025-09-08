package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;

public record LivreurDtoResponse(
        Long id,
        String nom,
        String prenom,
        String telephone,
        StatutLivreur status
) {
}
