package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LivreurService {
    public ResponseEntity<LivreurDtoResponse> create(LivreurDtoRequest livreurDtoRequest);
    public ResponseEntity <LivreurDtoResponse> getLivreur(Long id);
    public List<LivreurDtoResponse> getAllLivreur();
    public ResponseEntity <LivreurDtoResponse> updateLivreur(Long id,LivreurDtoRequest livreurDtoRequest);
    public String delete(Long id);
}
