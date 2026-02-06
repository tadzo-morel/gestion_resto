package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findByNom(String nom);
    
    // AJOUT: Méthodes supplémentaires utiles
    Optional<Client> findByEmail(String email);
    
    Optional<Client> findByTelephone(String telephone);
    
    List<Client> findByLocalisationContainingIgnoreCase(String localisation);
    
    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> searchByNomOrPrenom(@Param("keyword") String keyword);
    
    // Trouver clients avec leurs commandes
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.commandes WHERE c.id = :id")
    Optional<Client> findByIdWithCommandes(@Param("id") Long id);
    
    // Compter le nombre de commandes par client
    @Query("SELECT c, COUNT(cmd) FROM Client c LEFT JOIN c.commandes cmd GROUP BY c")
    List<Object[]> countCommandesByClient();
}