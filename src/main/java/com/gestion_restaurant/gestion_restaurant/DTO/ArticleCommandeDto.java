package com.gestion_restaurant.gestion_restaurant.DTO;

public record ArticleCommandeDto(
        Long articleId,
        Integer quantite,
        String commentaire // Optionnel
) {
    public ArticleCommandeDto {
        if (quantite == null || quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
    }
}