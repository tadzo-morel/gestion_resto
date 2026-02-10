package com.gestion_restaurant.gestion_restaurant.security.config;

import com.gestion_restaurant.gestion_restaurant.security.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.ws.rs.HttpMethod;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Utilisez ceci pour simplifier l'injection
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter; // <--- AJOUTEZ CECI

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // On lie explicitement votre service de récupération d'utilisateurs
        authProvider.setUserDetailsService(userDetailsService);
        // On lie l'encodeur de mot de passe injecté
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. ACCÈS PUBLIC
                        .requestMatchers("/api/auth/**", "/error").permitAll()

                        // 2. ADMINISTRATION (Seul l'ADMIN peut gérer le personnel, les tables et le menu)
                        .requestMatchers("/livreur/**").hasRole("ADMIN")
                        .requestMatchers("/client/").hasRole("ADMIN") // Voir la liste de tous les clients
                        .requestMatchers("/salle/create/**", "/salle/update/**", "/salle/delete/**").hasRole("ADMIN")
                        .requestMatchers("/table/**").hasRole("ADMIN")
                        .requestMatchers("/articleMenu/create/**", "/articleMenu/update/**", "/articleMenu/delete/**").hasRole("ADMIN")
                        .requestMatchers("/facture/**").hasRole("ADMIN")

                        // 3. CLIENTS (Réservations et Commandes)
                        .requestMatchers("/reservation/**").hasAnyRole("CLIENT", "ADMIN")
                        .requestMatchers("/commande/**").hasAnyRole("CLIENT", "ADMIN")
                        .requestMatchers("/paiement/**").hasAnyRole("CLIENT", "ADMIN")

                        // 4. LIVREURS (Peuvent voir les commandes assignées)
                        .requestMatchers("/commande/nomLivreur/**").hasAnyRole("LIVREUR", "ADMIN")

                        // 5. CONSULTATION (Tous les utilisateurs connectés peuvent voir le menu ou les salles)
                        .requestMatchers(HttpMethod.GET, "/articleMenu/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/salle/**").authenticated()

                        // Tout le reste nécessite d'être simplement authentifié
                        .anyRequest().authenticated()
                )
                // Ajout du filtre JWT avant le filtre d'authentification de Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}