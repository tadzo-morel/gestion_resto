package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Reservation;
import com.gestion_restaurant.gestion_restaurant.entity.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    Optional<Reservation> findByNbrePersonne(Integer nbrePersonne);
    
    // AJOUT: Méthodes plus utiles
    List<Reservation> findByClientId(Long clientId);
    
    List<Reservation> findByDateReservation(LocalDate date);
    
    List<Reservation> findByStatus(StatutReservation status);
    
    @Query("SELECT r FROM Reservation r WHERE r.dateReservation = :date AND r.status = :status")
    List<Reservation> findReservationsConfirmeesByDate(@Param("date") LocalDate date, @Param("status") StatutReservation status);
    
    @Query("SELECT r FROM Reservation r WHERE r.nbrePersonne >= :minPersonnes")
    List<Reservation> findByNbrePersonneGreaterThanEqual(@Param("minPersonnes") Integer minPersonnes);
    
    // Récupérer réservation avec détails
    @Query("SELECT r FROM Reservation r " +
           "LEFT JOIN FETCH r.client " +
           "LEFT JOIN FETCH r.table " +
           "WHERE r.id = :id")
    Optional<Reservation> findByIdWithDetails(@Param("id") Long id);
}