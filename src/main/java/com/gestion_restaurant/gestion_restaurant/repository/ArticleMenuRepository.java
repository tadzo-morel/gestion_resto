package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.ArticleMenu;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleMenuRepository extends JpaRepository<ArticleMenu, Long> {
    
    // AJOUT: Méthodes de recherche utiles
    List<ArticleMenu> findByNomPlatContainingIgnoreCase(String nom);
    
    List<ArticleMenu> findByPrixLessThanEqual(Double prix);
    
    List<ArticleMenu> findByPrixBetween(Double prixMin, Double prixMax);
    
    @Query("SELECT a FROM ArticleMenu a WHERE LOWER(a.nomPlat) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(a.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ArticleMenu> searchByNomOrDescription(@Param("keyword") String keyword);
    
    // Articles les plus populaires (avec le plus de commandes)
    @Query("SELECT a, COUNT(lc) as nombreCommandes " +
           "FROM ArticleMenu a " +
           "LEFT JOIN a.commandes lc " +
           "GROUP BY a.id " +
           "ORDER BY nombreCommandes DESC")
    List<Object[]> findMostPopularArticles();
    
    // Trouver article avec ses lignes de commande
    @Query("SELECT a FROM ArticleMenu a LEFT JOIN FETCH a.commandes WHERE a.id = :id")
    Optional<ArticleMenu> findByIdWithLignesDeCommande(@Param("id") Long id);
}