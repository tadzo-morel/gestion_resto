package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.LigneDeCommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeRepository extends JpaRepository<LigneDeCommande, Long> {
    
    // AJOUT: Méthodes utiles
    List<LigneDeCommande> findByCommandeId(Long commandeId);
    
    List<LigneDeCommande> findByArticleMenuId(Long articleMenuId);
    
    @Query("SELECT lc FROM LigneDeCommande lc WHERE lc.commande.id = :commandeId " +
           "AND lc.articleMenu.id = :articleMenuId")
    Optional<LigneDeCommande> findByCommandeAndArticle(@Param("commandeId") Long commandeId,
                                                      @Param("articleMenuId") Long articleMenuId);
    
    // Calculer le total d'une commande
    @Query("SELECT SUM(lc.quantite_article * a.prix) " +
           "FROM LigneDeCommande lc " +
           "JOIN lc.articleMenu a " +
           "WHERE lc.commande.id = :commandeId")
    Double calculateTotalByCommandeId(@Param("commandeId") Long commandeId);
    
    // Articles les plus commandés
    @Query("SELECT a.nomPlat, SUM(lc.quantite_article) as totalQuantite " +
           "FROM LigneDeCommande lc " +
           "JOIN lc.articleMenu a " +
           "GROUP BY a.id " +
           "ORDER BY totalQuantite DESC")
    List<Object[]> findMostOrderedArticles();
}