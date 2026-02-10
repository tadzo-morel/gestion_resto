package com.gestion_restaurant.gestion_restaurant.dto;

import com.gestion_restaurant.gestion_restaurant.entity.ModePaiement;
import java.time.LocalDate;
import java.time.LocalTime;

public record PaiementDtoRequest(
        LocalDate datePaiement, // camelCase
        LocalTime heurePaiement, // camelCase
        Double montant, // Changer de double à Double
        ModePaiement mode,
        Long commandeId,
        String numeroTransaction // Pour paiements mobiles
) {
    public PaiementDtoRequest {
        if (datePaiement == null) {
            datePaiement = LocalDate.now();
        }
        if (heurePaiement == null) {
            heurePaiement = LocalTime.now();
        }
        if (mode == null) {
            mode = ModePaiement.bancaire; // Valeur par défaut
        }
    }
    
    // Constructeur simplifié
    public PaiementDtoRequest(Double montant, ModePaiement mode, Long commandeId) {
        this(LocalDate.now(), LocalTime.now(), montant, mode, commandeId, null);
    }
}