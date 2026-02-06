package com.gestion_restaurant.gestion_restaurant.DTO;

import java.time.LocalDate;

public record DashboardStatsDto(
        Long totalCommandes,
        Long commandesAujourdhui,
        Double revenuTotal,
        Double revenuMensuel,
        Long clientsInscrits,
        Long reservationsAujourdhui,
        Long livreursDisponibles,
        LocalDate date
) {
    public DashboardStatsDto {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}