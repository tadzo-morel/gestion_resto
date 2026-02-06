package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    
    // Recherche par numéro de compte
    @Query("SELECT f FROM Facture f WHERE f.num_compte = :num_compte")
    Optional<Facture> findByNum_compte(@Param("num_compte") String num_compte);
    
    // Recherche par ID de commande
    @Query("SELECT f FROM Facture f WHERE f.command.id = :commandeId")
    Optional<Facture> findByCommandeId(@Param("commandeId") Long commandeId);
    
    // Recherche par ID de paiement
    @Query("SELECT f FROM Facture f WHERE f.paiement.id = :paiementId")
    Optional<Facture> findByPaiementId(@Param("paiementId") Long paiementId);
    
    // Factures sans paiement associé (peut être utile pour le debug)
    @Query("SELECT f FROM Facture f WHERE f.paiement IS NULL")
    List<Facture> findFacturesWithoutPaiement();
    
    // Factures avec paiement associé
    @Query("SELECT f FROM Facture f WHERE f.paiement IS NOT NULL")
    List<Facture> findFacturesWithPaiement();
    
    // Recherche de factures par partie du numéro de compte
    @Query("SELECT f FROM Facture f WHERE f.num_compte LIKE %:numeroPart%")
    List<Facture> findByNumCompteContaining(@Param("numeroPart") String numeroPart);
    
    // Récupérer facture avec toutes les relations
    @Query("SELECT f FROM Facture f " +
           "LEFT JOIN FETCH f.command cmd " +
           "LEFT JOIN FETCH cmd.client " +
           "LEFT JOIN FETCH f.paiement " +
           "WHERE f.id = :id")
    Optional<Facture> findByIdWithDetails(@Param("id") Long id);
    
    // Générer un nouveau numéro de compte unique
    @Query(value = "SELECT CONCAT('FACT-', LPAD(COALESCE(MAX(CAST(SUBSTRING(num_compte, 6) AS UNSIGNED)), 0) + 1, 6, '0')) " +
           "FROM facture WHERE num_compte LIKE 'FACT-%'", 
           nativeQuery = true)
    String generateNewNumCompte();
    
    // Statistiques des factures
    @Query("SELECT COUNT(f), COALESCE(SUM(p.montant), 0) " +
           "FROM Facture f " +
           "LEFT JOIN f.paiement p " +
           "WHERE f.paiement IS NOT NULL")
    Object[] getFacturationStatistics();
    
    // Vérifier si une commande a déjà une facture
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
           "FROM Facture f WHERE f.command.id = :commandeId")
    boolean existsByCommandeId(@Param("commandeId") Long commandeId);
}