package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.ModePaiement;
import com.gestion_restaurant.gestion_restaurant.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    
    // Recherche par mode de paiement
    List<Paiement> findByMode(ModePaiement mode);
    
    // Recherche par montant
    List<Paiement> findByMontantGreaterThan(Double montant);
    
    // Recherche par date de paiement
    @Query("SELECT p FROM Paiement p WHERE p.date_paiement = :date")
    List<Paiement> findByDatePaiement(@Param("date") LocalDate date);
    
    // Recherche par commande ID
    @Query("SELECT p FROM Paiement p WHERE p.commands.id = :commandeId")
    Optional<Paiement> findByCommandeId(@Param("commandeId") Long commandeId);
    
    // Recherche entre deux dates
    @Query("SELECT p FROM Paiement p WHERE p.date_paiement BETWEEN :startDate AND :endDate")
    List<Paiement> findBetweenDates(@Param("startDate") LocalDate startDate, 
                                   @Param("endDate") LocalDate endDate);
    
    // Total des paiements par jour
    @Query("SELECT p.date_paiement, SUM(p.montant) " +
           "FROM Paiement p " +
           "WHERE p.date_paiement BETWEEN :startDate AND :endDate " +
           "GROUP BY p.date_paiement " +
           "ORDER BY p.date_paiement")
    List<Object[]> findDailyRevenue(@Param("startDate") LocalDate startDate, 
                                   @Param("endDate") LocalDate endDate);
    
    // Paiements avec facture associée
    @Query("SELECT p FROM Paiement p WHERE p.facture IS NOT NULL")
    List<Paiement> findPaiementsWithFacture();
    
    // Paiements sans facture
    @Query("SELECT p FROM Paiement p WHERE p.facture IS NULL")
    List<Paiement> findPaiementsWithoutFacture();
    
    // Recherche par heure de paiement (après une certaine heure)
    @Query("SELECT p FROM Paiement p WHERE p.heure_paiement > :heure")
    List<Paiement> findByHeurePaiementAfter(@Param("heure") LocalTime heure);
    // Paiements par mode avec total
    @Query("SELECT p.mode, COUNT(p), SUM(p.montant) " +
           "FROM Paiement p " +
           "GROUP BY p.mode")
    List<Object[]> findStatisticsByMode();
    
    // Récupérer paiement avec toutes les relations
    @Query("SELECT p FROM Paiement p " +
           "LEFT JOIN FETCH p.commands " +
           "LEFT JOIN FETCH p.facture " +
           "WHERE p.id = :id")
    Optional<Paiement> findByIdWithDetails(@Param("id") Long id);
    
    // Vérifier si une commande est déjà payée
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
           "FROM Paiement p WHERE p.commands.id = :commandeId")
    boolean existsByCommandeId(@Param("commandeId") Long commandeId);
}