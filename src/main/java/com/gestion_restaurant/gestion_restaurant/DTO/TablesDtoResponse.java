package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record TablesDtoResponse(
        Long id,
        StatusEspace status,
        int nbre_place
) {
}
