package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
  Optional <Client> findByNom(String nom);
}
