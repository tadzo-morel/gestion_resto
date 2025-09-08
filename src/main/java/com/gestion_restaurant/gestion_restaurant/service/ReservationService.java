package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.DTO.ReservationDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.ReservationDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    public ResponseEntity<ReservationDtoResponse> create(ReservationDtoRequest reservationDtoRequest);
    public ResponseEntity <ReservationDtoResponse> getReservation(Long id);
    public ResponseEntity<List<ReservationDtoResponse>> getAllReservation();
    public ResponseEntity <ReservationDtoResponse> updateReservation(Long id,ReservationDtoRequest reservationDtoRequest);
    public String delete(Long id);


}
