package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivreurRepository extends JpaRepository<Livreur,Long> {
    Optional <Livreur> findByNom(String nom);
}
