package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LigneCommandeService {
    public ResponseEntity<LigneDeCommandeDtoResponse> create(LigneDeCommandeDtoRequest ligneDeCommandeDtoRequest);
    public ResponseEntity <LigneDeCommandeDtoResponse> getLigneCommande(Long id);
    public ResponseEntity<List<LigneDeCommandeDtoResponse>> getAllLigneCommande();
    public ResponseEntity <LigneDeCommandeDtoResponse> updateLigneCommande(Long id, LigneDeCommandeDtoRequest ligneDeCommandeDtoRequest);
    public String delete(Long id);
}
