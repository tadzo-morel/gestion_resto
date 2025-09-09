package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;

public record LivreurDtoRequest(
         String nom,
         String prenom,
         String telephone,
         String email,
         String password,
         StatutLivreur status
) {
}
