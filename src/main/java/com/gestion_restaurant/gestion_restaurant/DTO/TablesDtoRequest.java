package com.gestion_restaurant.gestion_restaurant.dto;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record TablesDtoRequest(
    Integer nbrePlace,         // camelCase
    StatusEspace status,
    Long salleId               // ID de la salle
) {}