package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
public Optional <Reservation> findByNbrePersonne(int nbrePersonne);
}
