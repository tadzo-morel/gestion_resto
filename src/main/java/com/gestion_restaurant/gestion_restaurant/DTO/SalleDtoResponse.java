package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record
SalleDtoResponse(
        Long id,
        StatusEspace status,
        Long capacite
) {
}
