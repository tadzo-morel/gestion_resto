package com.gestion_restaurant.gestion_restaurant.service;


import com.gestion_restaurant.gestion_restaurant.DTO.ReservationDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.ReservationDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Reservation;
import com.gestion_restaurant.gestion_restaurant.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<ReservationDtoResponse> create(ReservationDtoRequest reservationDtoRequest) {
        Reservation reservation=new Reservation();
        reservation.setDateReservation(reservationDtoRequest.date_reservation());
        reservation.setHeure(reservationDtoRequest.heure_reservation());
        reservation.setNbrePersonne(reservationDtoRequest.nbrePersonne());
        reservation.setStatus(reservationDtoRequest.status());
        Reservation newReservation=reservationRepository.save(reservation);
        ReservationDtoResponse reservationDtoResponse=new ReservationDtoResponse(
                newReservation.getId(),
                newReservation.getDateReservation(),
                newReservation.getHeure(),
                newReservation.getNbrePersonne(),
                newReservation.getStatus()
        );
        return new ResponseEntity<>(reservationDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReservationDtoResponse> getReservation(Long id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if (reservation.isPresent()){
            Reservation reservation1=reservation.get();
            ReservationDtoResponse reservationDtoResponse=new ReservationDtoResponse(
                    reservation1.getId(),
                    reservation1.getDateReservation(),
                    reservation1.getHeure(),
                    reservation1.getNbrePersonne(),
                    reservation1.getStatus()
            );

            return new ResponseEntity<>(reservationDtoResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ReservationDtoResponse>> getAllReservation() {
        List<ReservationDtoResponse>reservationDtoResponses=new ArrayList<>();
        List<Reservation> reservations=reservationRepository.findAll();
        for (Reservation reservation:reservations){
            reservationDtoResponses.add(new ReservationDtoResponse(
                    reservation.getId(),
                    reservation.getDateReservation(),
                    reservation.getHeure(),
                    reservation.getNbrePersonne(),
                    reservation.getStatus()
            ));
        }
        return new ResponseEntity<>(reservationDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReservationDtoResponse> updateReservation(Long id, ReservationDtoRequest reservationDtoRequest) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        reservation.setDateReservation(reservationDtoRequest.date_reservation());
        reservation.setHeure(reservationDtoRequest.heure_reservation());
        reservation.setNbrePersonne(reservationDtoRequest.nbrePersonne());
        reservation.setStatus(reservationDtoRequest.status());
        Reservation reservationNew =reservationRepository.save(reservation);

        ReservationDtoResponse reservationDtoResponse= new ReservationDtoResponse(
                reservationNew.getId(),
                reservationNew.getDateReservation(),
                reservationNew.getHeure(),
                reservationNew.getNbrePersonne(),
                reservationNew.getStatus()
        );
        return new ResponseEntity<>(reservationDtoResponse,HttpStatus.OK);
    }

    @Override
    public String delete(Long id) {
        reservationRepository.deleteById(id);
        return "Reservation supprimer";
    }
}
