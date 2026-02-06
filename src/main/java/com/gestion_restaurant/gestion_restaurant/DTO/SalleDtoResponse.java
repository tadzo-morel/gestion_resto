package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;
import java.util.List;

public record SalleDtoResponse(
        Long id,
        Integer capacite, // Changer de Long à Integer
        StatusEspace status,
        Long reservationId, // Optionnel
        Integer nbreTables,
        List<TablesDtoResponse> tables // Nouveau: liste des tables
) {
    public SalleDtoResponse {
        if (tables == null) {
            tables = List.of();
        }
        if (nbreTables == null && tables != null) {
            nbreTables = tables.size();
        }
    }
    
    // Constructeur simplifié (compatibilité)
    public SalleDtoResponse(Long id, Long capacite, StatusEspace status, int nbrePersonne) {
        this(id, capacite != null ? capacite.intValue() : 0, status, null, 0, List.of());
    }
}