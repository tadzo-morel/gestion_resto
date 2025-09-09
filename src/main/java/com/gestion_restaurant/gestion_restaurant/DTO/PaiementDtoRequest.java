package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.ModePaiement;

import java.time.LocalDate;
import java.time.LocalTime;

public record PaiementDtoRequest(
        LocalDate date_paiement,
        LocalTime heure_paiement,
        double montant,
        ModePaiement mode
) {
}
