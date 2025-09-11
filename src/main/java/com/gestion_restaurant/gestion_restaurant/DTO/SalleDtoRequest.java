package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record SalleDtoRequest(
        StatusEspace status,
        Long capacite
) {
}
