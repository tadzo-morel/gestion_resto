package com.gestion_restaurant.gestion_restaurant.security.service;

import com.gestion_restaurant.gestion_restaurant.entity.*;
import com.gestion_restaurant.gestion_restaurant.repository.UserRepository;
import com.gestion_restaurant.gestion_restaurant.security.dto.LoginRequest;
import com.gestion_restaurant.gestion_restaurant.security.dto.LoginResponse;
import com.gestion_restaurant.gestion_restaurant.security.dto.RegisterRequest;
import com.gestion_restaurant.gestion_restaurant.security.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    // Dans AuthService.java
    public LoginResponse login(LoginRequest request) {
        // 1. Authentification via Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // 2. Récupération de l'utilisateur en base
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // 3. Génération du token JWT
        // On utilise les informations de l'utilisateur pour créer le token
        String jwtToken = jwtService.generateToken(
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(determineRole(user)) // Votre méthode qui renvoie ROLE_ADMIN, etc.
                        .build()
        );

        // 4. On renvoie l'objet LoginResponse avec le token à l'intérieur
        return new LoginResponse(jwtToken);
    }
    public UserInfoDto register(RegisterRequest request) {
        User user;

        // Choix de la classe à instancier selon le rôle
        switch (request.userType().toUpperCase()) {
            case "ADMIN" -> {
                Admin admin = new Admin();
                admin.setDroit(request.droit());
                user = admin;
            }
            case "LIVREUR" -> {
                Livreur livreur = new Livreur();
                // Assurez-vous d'avoir une méthode pour convertir le String en Enum StatutLivreur
                livreur.setStatus(livreur.getStatus());
                user = livreur;
            }
            default -> { // Par défaut, on crée un Client
                Client client = new Client();
                client.setLocalisation(request.localisation());
                user = client;
            }
        }

        // Remplissage des champs communs (hérités de User)
        user.setNom(request.nom());
        user.setPrenom(request.prenom());
        user.setEmail(request.email());
        user.setTelephone(request.telephone());
        user.setPassword(passwordEncoder.encode(request.password()));

        // Sauvegarde en base (JPA gérera l'insertion dans les bonnes tables grâce à @Inheritance)
        User savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    private UserInfoDto mapToDto(User user) {
        return new UserInfoDto(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getTelephone(),
                determineRole(user),
                user.getClass().getSimpleName() // Retourne "Admin", "Client" ou "Livreur"
        );
    }

    private String determineRole(User user) {
        if (user instanceof Admin) return "ROLE_ADMIN";
        if (user instanceof Livreur) return "ROLE_LIVREUR";
        return "ROLE_CLIENT";
    }
    public UserInfoDto getCurrentUser() {
        // 1. Récupérer l'email de l'utilisateur actuellement connecté au système
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Aucun utilisateur n'est actuellement connecté");
        }

        String email = authentication.getName();

        // 2. Chercher l'utilisateur complet en base de données
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email));

        // 3. Retourner le DTO complet
        return new UserInfoDto(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getTelephone(),
                determineRole(user),             // Appel de la méthode qui renvoie ROLE_ADMIN, etc.
                user.getClass().getSimpleName()   // Renvoie "Admin", "Client" ou "Livreur"
        );
    }
}