package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.LivreurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/livreur")
public class LivreurController {
    private final LivreurService livreurService;
    @PostMapping("/create")
    public ResponseEntity<LivreurDtoResponse> create(@RequestBody LivreurDtoRequest livreur){
        return livreurService.create(livreur);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LivreurDtoResponse> getById(@PathVariable Long id){
        return livreurService.getLivreur(id);
    }
    @GetMapping("/")
    public ResponseEntity<List<LivreurDtoResponse>> getAll(){
        return livreurService.getAllLivreur();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity <LivreurDtoResponse > update(@PathVariable Long id,@RequestBody LivreurDtoRequest livreur){
        return livreurService.updateLivreur(id,livreur);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return livreurService.delete(id);
    }
    @GetMapping("/nom/{nom}")
    public ResponseEntity<LivreurDtoResponse> findByNom(@PathVariable String nom){
        return livreurService.findByName(nom);
    }
}
