package com.gestion_restaurant.gestion_restaurant.DTO;

import java.time.LocalDateTime;

public record FactureDtoResponse(
        Long id,
        String numCompte, // camelCase et correction orthographique
        LocalDateTime dateGeneration,
        Double montantTotal,
        Long commandeId,
        Long paiementId,
        String clientNom,
        String clientEmail
) {
    public FactureDtoResponse {
        if (dateGeneration == null) {
            dateGeneration = LocalDateTime.now();
        }
    }
    
    // Constructeur simplifié
    public FactureDtoResponse(Long id, String numCompte) {
        this(id, numCompte, LocalDateTime.now(), null, null, null, null, null);
    }
}