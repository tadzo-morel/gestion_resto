package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.FactureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/facture")
public class FactureController {
    private final FactureService factureService;
    @PostMapping("/create")
    public ResponseEntity<FactureDtoResponse> create(@RequestBody FactureDtoRequest facture){
        return factureService.create(facture);
    }
    @GetMapping("/")
    public ResponseEntity<List<FactureDtoResponse>> getAllFacture(){
        return factureService.getAllFacture();
    }
    @GetMapping("/{id}")
    public ResponseEntity<FactureDtoResponse> get(@PathVariable Long id){
        return factureService.getFacture(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FactureDtoResponse> update(@PathVariable Long id, @RequestBody FactureDtoRequest facture ){
        return factureService.updateFacture(id,facture);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return factureService.delete(id);
    }
}
