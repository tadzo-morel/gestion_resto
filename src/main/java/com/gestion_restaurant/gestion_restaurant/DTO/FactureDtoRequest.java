package com.gestion_restaurant.gestion_restaurant.DTO;

public record FactureDtoRequest(
        String numCompte, // camelCase
        Long commandeId,
        Long paiementId // Optionnel
) {
    public FactureDtoRequest {
        // Validation
        if (commandeId == null) {
            throw new IllegalArgumentException("commandeId ne peut pas être null");
        }
    }
    
    // Constructeur pour création automatique
    public FactureDtoRequest(Long commandeId) {
        this(null, commandeId, null);
    }
}