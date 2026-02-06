package com.gestion_restaurant.gestion_restaurant.DTO;

public record LigneDeCommandeDtoResponse(
        Long id,
        Integer quantiteArticle, // Changer de Long à Integer
        Long commandeId,
        Long articleMenuId,
        String articleNom,
        Double prixUnitaire,
        Double sousTotal
) {
    public LigneDeCommandeDtoResponse {
        // Calcul automatique du sous-total
        if (prixUnitaire != null && quantiteArticle != null) {
            sousTotal = prixUnitaire * quantiteArticle;
        } else {
            sousTotal = null;
        }
    }
    
    // Constructeur simplifié
    public LigneDeCommandeDtoResponse(Long id, Integer quantiteArticle) {
        this(id, quantiteArticle, null, null, null, null, null);
    }
}
