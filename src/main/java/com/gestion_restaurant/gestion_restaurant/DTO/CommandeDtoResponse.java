package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.StatutCommande;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CommandeDtoResponse(
        Long id,
        LocalDate dateCommande,
        LocalTime heureCommande,
        LocalDate dateLivraison,
        LocalTime heureLivraison,
        Double montant,
        String nomClient,
        String nomLivreur, // Peut être null
        StatutCommande status,
        String localisation,
        
        // Nouveau: Détails des articles
        List<LigneDeCommandeDtoResponse> lignesDeCommande
) {
    public CommandeDtoResponse {
        if (lignesDeCommande == null) {
            lignesDeCommande = List.of();
        }
    }
    
    // Constructeur simplifié (sans lignes)
    public CommandeDtoResponse(
            Long id,
            LocalDate dateCommande,
            LocalTime heureCommande,
            LocalDate dateLivraison,
            LocalTime heureLivraison,
            Double montant,
            String nomClient,
            String nomLivreur,
            StatutCommande status,
            String localisation
    ) {
        this(id, dateCommande, heureCommande, dateLivraison, heureLivraison,
             montant, nomClient, nomLivreur, status, localisation, List.of());
    }
}
