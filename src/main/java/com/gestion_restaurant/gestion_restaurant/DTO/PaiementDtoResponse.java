package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.ModePaiement;
import java.time.LocalDate;
import java.time.LocalTime;

public record PaiementDtoResponse(
        Long id,
        LocalDate datePaiement, // camelCase
        LocalTime heurePaiement, // camelCase
        Double montant,
        ModePaiement mode,
        Long commandeId,
        String numeroTransaction,
        Boolean estValide,
        String factureNumero
) {
    public PaiementDtoResponse {
        if (estValide == null) {
            estValide = true;
        }
    }
    
    // Constructeur simplifié
    public PaiementDtoResponse(Long id, LocalDate datePaiement, LocalTime heurePaiement, 
                               Double montant, ModePaiement mode) {
        this(id, datePaiement, heurePaiement, montant, mode, null, null, true, null);
    }
}