package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.repository.SalleRepository;
import com.gestion_restaurant.gestion_restaurant.service.SalleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/salle")
public class SalleController {
    private final SalleService salleService;
    @PostMapping("/create")
    public ResponseEntity<SalleDtoResponse> create(@RequestBody SalleDtoRequest salle){
        return salleService.create(salle);
    }
    @GetMapping("/")
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle(){
        return salleService.getAllSalle();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalleDtoResponse> get(@PathVariable Long id){
        return salleService.getSalle(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<SalleDtoResponse> update(@PathVariable Long id, @RequestBody SalleDtoRequest salle){
        return salleService.updateSalle(id,salle);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return salleService.delete(id);
    }
}
