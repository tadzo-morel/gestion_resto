package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record TablesDtoRequest(
        int nbre_place,
        StatusEspace status
) {
}
