package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.FactureService;
import com.gestion_restaurant.gestion_restaurant.service.LigneCommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ligneCommande")
public class LigneCommandeController {

    private final LigneCommandeService ligneCommandeService;
    @PostMapping("/create")
    public ResponseEntity<LigneDeCommandeDtoResponse> create(@RequestBody LigneDeCommandeDtoRequest ligneDeCommande){
        return ligneCommandeService.create(ligneDeCommande);
    }
    @GetMapping("/")
    public ResponseEntity<List<LigneDeCommandeDtoResponse>> getAllLigneCommande(){
        return ligneCommandeService.getAllLigneCommande();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LigneDeCommandeDtoResponse> get(@PathVariable Long id){
        return ligneCommandeService.getLigneCommande(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LigneDeCommandeDtoResponse> update(@PathVariable Long id, @RequestBody LigneDeCommandeDtoRequest ligneDeCommande ){
        return ligneCommandeService.updateLigneCommande(id,ligneDeCommande);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return ligneCommandeService.delete(id);
    }
}
