package com.gestion_restaurant.gestion_restaurant.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.gestion_restaurant.gestion_restaurant.entity.StatutCommande;

public record CommandeDtoRequest(
        // Dates et heures (dateCommande générée automatiquement)
        LocalDate dateCommande,
        LocalTime heureCommande,
        LocalDate dateLivraison,
        LocalTime heureLivraison,
        
        // Informations de base
        StatutCommande status,
        String localisation,
        
        // Références aux autres entités
        String nomClient,
        String nomLivreur, // Optionnel
        
        // Montant (peut être calculé automatiquement)
        Double montant, // Changer de double à Double (nullable)
        
        // Nouveau: Liste des articles commandés
        List<ArticleCommandeDto> articles
) {
    // Constructeur pour compatibilité
    public CommandeDtoRequest {
        if (montant == null) {
            montant = 0.0;
        }
        if (articles == null) {
            articles = List.of();
        }
    }
    
    // Constructeur simplifié (sans articles)
    public CommandeDtoRequest(
            LocalDate dateCommande,
            LocalTime heureCommande,
            LocalDate dateLivraison,
            LocalTime heureLivraison,
            StatutCommande status,
            String localisation,
            String nomClient,
            String nomLivreur,
            Double montant
    ) {
        this( dateCommande, heureCommande, dateLivraison, heureLivraison, status, localisation, 
             nomClient, nomLivreur, montant, List.of());
    }
}