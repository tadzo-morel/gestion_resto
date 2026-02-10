package com.gestion_restaurant.gestion_restaurant.security.dto;

import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank(message = "Le nom est obligatoire")
    String nom,
    
    @NotBlank(message = "Le prénom est obligatoire")
    String prenom,
    
    @NotBlank(message = "Le téléphone est obligatoire")
    String telephone,
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    String email,
    
    @NotBlank(message = "Le mot de passe est obligatoire")
    String password,
    
    @NotBlank(message = "Le type d'utilisateur est obligatoire")
    String userType, // "CLIENT", "LIVREUR", "ADMIN"
    
    // Champs spécifiques selon le type
    String localisation, // Pour CLIENT
    StatutLivreur status, // Pour LIVREUR
    String droit // Pour ADMIN
) {}
