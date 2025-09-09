package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/paiement")
public class PaiementController {
    private final PaiementService paiementService;
    @PostMapping("/create")
    public ResponseEntity<PaiementDtoResponse> create(@RequestBody PaiementDtoRequest paiement){
        return paiementService.create(paiement);
    }
    @GetMapping("/")
    public ResponseEntity<List<PaiementDtoResponse>> getAllPaiement(){
        return paiementService.getAllPaiement();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaiementDtoResponse> get(@PathVariable Long id){
        return paiementService.getPaiement(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PaiementDtoResponse> update(@PathVariable Long id, @RequestBody PaiementDtoRequest paiement){
        return paiementService.updatePaiement(id,paiement);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return paiementService.delete(id);
    }
}
