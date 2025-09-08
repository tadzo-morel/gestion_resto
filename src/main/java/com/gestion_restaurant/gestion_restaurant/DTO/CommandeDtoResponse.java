package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutCommande;

import java.time.LocalDate;
import java.time.LocalTime;

public record CommandeDtoResponse(
        Long id,
        LocalDate dateCommande,
        LocalTime heureCommande,
        LocalDate dateLivraison,
        LocalTime heureLivraison,
        double montant,
        StatutCommande status,
        String localisation
) {
}
