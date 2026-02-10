package com.gestion_restaurant.gestion_restaurant.security.service;

import com.gestion_restaurant.gestion_restaurant.entity.User;
import com.gestion_restaurant.gestion_restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email));
        
        // Déterminez le rôle en fonction du type d'utilisateur
        String role = determineRole(user);
        
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
    
    private String determineRole(User user) {
        // Adaptez cette logique à votre hiérarchie d'utilisateur
        if (user instanceof com.gestion_restaurant.gestion_restaurant.entity.Admin) {
            return "ROLE_ADMIN";
        } else if (user instanceof com.gestion_restaurant.gestion_restaurant.entity.Livreur) {
            return "ROLE_LIVREUR";
        } else if (user instanceof com.gestion_restaurant.gestion_restaurant.entity.Client) {
            return "ROLE_CLIENT";
        } else {
            return "ROLE_USER";
        }
    }
}