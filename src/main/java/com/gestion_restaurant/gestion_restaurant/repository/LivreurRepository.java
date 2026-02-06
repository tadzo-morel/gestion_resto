package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import com.gestion_restaurant.gestion_restaurant.entity.StatutLivreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    
    Optional<Livreur> findByNom(String nom);
    
    // AJOUT: Méthodes utiles
    List<Livreur> findByStatus(StatutLivreur status);
    
    @Query("SELECT l FROM Livreur l WHERE l.status = :status")
    List<Livreur> findLivreursDisponibles(@Param("status") StatutLivreur status);
    
    Optional<Livreur> findByEmail(String email);
    
    // Trouver livreur avec ses commandes
    @Query("SELECT l FROM Livreur l LEFT JOIN FETCH l.commandes WHERE l.id = :id")
    Optional<Livreur> findByIdWithCommandes(@Param("id") Long id);
    
    // Compter commandes par livreur
    @Query("SELECT l.nom, COUNT(c) FROM Livreur l LEFT JOIN l.commandes c GROUP BY l.id")
    List<Object[]> countCommandesByLivreur();
}