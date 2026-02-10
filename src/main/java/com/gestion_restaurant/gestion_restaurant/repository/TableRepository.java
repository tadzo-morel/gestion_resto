package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Long> {
    
    // Trouver tables par salle
    List<Tables> findBySalleId(Long salleId);
    
    // Trouver tables disponibles
    List<Tables> findByStatus(StatusEspace status);
    
    // Trouver tables par nombre de places
    List<Tables> findByNbrePlace(Integer nbrePlace);
    
    // Trouver tables avec au moins X places
    List<Tables> findByNbrePlaceGreaterThanEqual(Integer minPlaces);
    
    // Trouver tables disponibles dans une salle spécifique
    @Query("SELECT t FROM Tables t WHERE t.salle.id = :salleId AND t.status = 'disponible'")
    List<Tables> findTablesDisponiblesBySalle(@Param("salleId") Long salleId);
    
    // Trouver tables par salle et nombre de places
    @Query("SELECT t FROM Tables t WHERE t.salle.id = :salleId AND t.nbrePlace >= :minPlaces")
    List<Tables> findBySalleAndCapacity(@Param("salleId") Long salleId, 
                                       @Param("minPlaces") Integer minPlaces);
    
    // Compter tables par salle
    @Query("SELECT COUNT(t) FROM Tables t WHERE t.salle.id = :salleId")
    Long countBySalleId(@Param("salleId") Long salleId);
}