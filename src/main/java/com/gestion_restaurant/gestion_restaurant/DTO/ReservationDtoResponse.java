package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutReservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDtoResponse(
        Long id,
        LocalDate dateReservation,
        LocalTime heureReservation,
        int nbrePersonne,
        StatutReservation status,
        String nomClient
) {
}
