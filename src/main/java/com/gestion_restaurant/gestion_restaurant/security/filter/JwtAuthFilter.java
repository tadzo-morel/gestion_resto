package com.gestion_restaurant.gestion_restaurant.security.filter;

import com.gestion_restaurant.gestion_restaurant.security.config.JwtConfig;
import com.gestion_restaurant.gestion_restaurant.security.service.JwtService;
import com.gestion_restaurant.gestion_restaurant.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        // 1. AJOUT : Ignorer explicitement les routes d'authentification
        // Cela permet de créer un compte sans avoir déjà un token
        String path = request.getServletPath();
        if (path.startsWith("/api/auth/register") || path.startsWith("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        final String authHeader = request.getHeader(jwtConfig.getAuthHeader());
        final String jwt;
        final String userEmail;
        
        // 2. Vérifie si le header Authorization existe et commence par le préfixe (ex: "Bearer ")
        if (authHeader == null || !authHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // 3. Extrait le token JWT
        jwt = authHeader.replace(jwtConfig.getTokenPrefix(), "").trim();
        userEmail = jwtService.extractUsername(jwt);
        
        // 4. Si l'email est extrait et qu'il n'y a pas d'authentification en cours
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            
            // 5. Valide le token avec le service JWT
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 6. Définit l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}