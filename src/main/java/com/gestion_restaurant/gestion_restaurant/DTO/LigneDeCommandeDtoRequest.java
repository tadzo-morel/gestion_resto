package com.gestion_restaurant.gestion_restaurant.DTO;

public record LigneDeCommandeDtoRequest(
        Integer quantiteArticle, // Changer de Long à Integer
        Long commandeId,
        Long articleMenuId
) {
    public LigneDeCommandeDtoRequest {
        // Validation
        if (quantiteArticle == null || quantiteArticle <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        if (commandeId == null) {
            throw new IllegalArgumentException("commandeId ne peut pas être null");
        }
        if (articleMenuId == null) {
            throw new IllegalArgumentException("articleMenuId ne peut pas être null");
        }
    }
}