package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import com.gestion_restaurant.gestion_restaurant.service.LivreurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/livreur")
public class LivreurController {
    private final LivreurService livreurService;
    @PostMapping("/create")
    public ResponseEntity<LivreurDtoResponse> create(LivreurDtoRequest livreur){
        return livreurService.create(livreur);
    }
}
