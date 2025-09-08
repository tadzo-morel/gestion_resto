package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatusEspaceReservable;

public record TablesDtoRequest(
        StatusEspaceReservable status,
        int nbre_place
) {
}
