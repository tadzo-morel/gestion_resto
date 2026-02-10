package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;
import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    
    // Méthode pour trouver les tables par nombre de places
    @Query("SELECT t FROM Salle s JOIN s.tables t WHERE t.nbrePlace = :nbrePlace")
    List<Tables> findTablesByNbrePlace(@Param("nbrePlace") Integer nbrePlace);
    
    // Méthodes utiles supplémentaires
    @Query("SELECT s FROM Salle s WHERE s.capacite >= :capaciteMin")
    List<Salle> findByCapaciteGreaterThanEqual(@Param("capaciteMin") Integer capaciteMin);
    
    @Query("SELECT s FROM Salle s WHERE s.status = :status")
    List<Salle> findSallesDisponibles(@Param("status") StatusEspace status);
    
    // Compter le nombre de tables dans une salle
    @Query("SELECT COUNT(t) FROM Salle s JOIN s.tables t WHERE s.id = :salleId")
    Long countTablesBySalleId(@Param("salleId") Long salleId);
    
    // Trouver salles avec leurs tables
    @Query("SELECT s FROM Salle s LEFT JOIN FETCH s.tables WHERE s.id = :id")
    Salle findByIdWithTables(@Param("id") Long id);
}
