package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutCommande;

import java.time.LocalDate;
import java.time.LocalTime;

public record CommandeDtoRequest(
        LocalDate dateCommande,
        LocalDate dateLivraison,
        LocalTime heureCommande,
        LocalTime heureLivraison,
        double montant,
        StatutCommande status,
        String localisation
) {
}
