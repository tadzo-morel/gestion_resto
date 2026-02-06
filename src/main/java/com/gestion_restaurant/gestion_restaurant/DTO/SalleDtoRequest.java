package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record SalleDtoRequest(
        Integer capacite, // Changer de Long à Integer
        StatusEspace status,
        Long reservationId // Optionnel, pas nbrePersonne
) {
    public SalleDtoRequest {
        if (status == null) {
            status = StatusEspace.disponible;
        }
        if (capacite == null || capacite <= 0) {
            throw new IllegalArgumentException("La capacité doit être positive");
        }
    }
    
    // Constructeur simplifié
    public SalleDtoRequest(Integer capacite) {
        this(capacite, StatusEspace.disponible, null);
    }
}