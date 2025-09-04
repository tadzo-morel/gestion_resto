package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;

public record LivreurDtoRequest(
        Long id,
        String nom,
        String prenom,
        String phoneNumber,
        String email,
        StatutLivreur status,
        String password
) {
}
