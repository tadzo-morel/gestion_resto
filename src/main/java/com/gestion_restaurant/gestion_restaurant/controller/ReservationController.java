package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.dto.ReservationDtoRequest;
import com.gestion_restaurant.gestion_restaurant.dto.ReservationDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping("/create")
    public ResponseEntity<ReservationDtoResponse> create(@RequestBody ReservationDtoRequest reservation){
        return reservationService.create(reservation);
    }
    @GetMapping("/")
    public ResponseEntity<List<ReservationDtoResponse>> getAllReservation(){
        return reservationService.getAllReservation();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDtoResponse> getReservation(@PathVariable Long id){
        return reservationService.getReservation(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDtoResponse> update(@PathVariable Long id, @RequestBody ReservationDtoRequest reservation){
        return reservationService.updateReservation(id,reservation);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return reservationService.delete(id);
    }
}
