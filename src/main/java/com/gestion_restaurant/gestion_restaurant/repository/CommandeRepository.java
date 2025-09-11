package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("select co from Client c join c.commandes co where c.nom = :nom")
    public List<Commande> getAllCommandeFromClient(@Param("nom") String nom);
    @Query("select co from Livreur l Join commandes co where l.nom =:nom")
    public List<Commande> getAllCommandeFromLivreur(@Param("nom") String nom);

}
