package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutReservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDtoRequest(
        LocalDate date_reservation,
        LocalTime heure_reservation,
        int nbrePersonne,
        StatutReservation status
) {
}
