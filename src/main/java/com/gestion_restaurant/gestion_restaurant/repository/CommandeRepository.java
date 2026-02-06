package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import com.gestion_restaurant.gestion_restaurant.entity.StatutCommande;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    
    @Query("SELECT c FROM Client cl JOIN cl.commandes c WHERE cl.nom = :nom")
    List<Commande> getAllCommandeFromClient(@Param("nom") String nom);
    
    @Query("SELECT c FROM Livreur l JOIN l.commandes c WHERE l.nom = :nom")
    List<Commande> getAllCommandeFromLivreur(@Param("nom") String nom);
    
    List<Commande> findByStatus(StatutCommande status);
    
    List<Commande> findByClientId(Long clientId);
    
    List<Commande> findByLivreurId(Long livreurId);
    
    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :date")
    List<Commande> findByDate(@Param("date") LocalDate date);
    
    @Query("SELECT c FROM Commande c WHERE c.montant > :montantMin")
    List<Commande> findByMontantGreaterThan(@Param("montantMin") Double montantMin);
    
    @Query("SELECT c FROM Commande c " +
           "LEFT JOIN FETCH c.client " +
           "LEFT JOIN FETCH c.livreur " +
           "LEFT JOIN FETCH c.articleMenu " +
           "WHERE c.id = :id")
    Commande findByIdWithDetails(@Param("id") Long id);
}