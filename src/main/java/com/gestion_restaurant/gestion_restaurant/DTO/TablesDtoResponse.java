package com.gestion_restaurant.gestion_restaurant.dto;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record TablesDtoResponse(
    Long id,
    StatusEspace status,
    Integer nbrePlace,
    Long salleId,              // ID de la salle
    String salleNom            // Optionnel: nom ou info de la salle
) {}