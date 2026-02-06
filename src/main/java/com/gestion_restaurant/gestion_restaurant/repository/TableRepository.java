package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Long> {
    
    // AJOUT: Méthodes utiles
    @Query("SELECT t FROM Tables t WHERE t.nbre_place = :nbrePlace")
    List<Tables> findByNbrePlace(@Param("nbrePlace") Integer nbrePlace);
    
    @Query("SELECT t FROM Tables t WHERE t.nbre_place >= :minPlaces")
    List<Tables> findByNbrePlaceGreaterThanEqual(@Param("minPlaces") Integer minPlaces);
    
    List<Tables> findByStatus(StatusEspace status);
    
    @Query("SELECT t FROM Tables t WHERE t.status = :status AND t.nbre_place >= :nbrePersonnes")
    List<Tables> findTablesDisponiblesByCapacity(@Param("status") StatusEspace status, @Param("nbrePersonnes") Integer nbrePersonnes);
    
    List<Tables> findBySalleId(Long salleId);
    
    // Tables disponibles dans une salle spécifique
    @Query("SELECT t FROM Tables t WHERE t.salle.id = :salleId AND t.status = :status")
    List<Tables> findTablesDisponiblesBySalle(@Param("salleId") Long salleId, @Param("status") StatusEspace status);
    
    // Compter tables par salle
    @Query("SELECT COUNT(t) FROM Salle s LEFT JOIN s.tables t GROUP BY s.id")
    List<Long> countTablesBySalle();
}