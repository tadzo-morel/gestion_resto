package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;

public record SalleDtoRequest(
        Long capacite,
        StatusEspace status,
        int nbrePersonne
) {
}
