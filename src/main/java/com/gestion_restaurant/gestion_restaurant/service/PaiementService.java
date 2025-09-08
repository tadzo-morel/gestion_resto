package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaiementService {
    public ResponseEntity<PaiementDtoResponse> create(PaiementDtoRequest paiementDtoRequest);
    public ResponseEntity <PaiementDtoResponse> getPaiement(Long id);
    public ResponseEntity<List<PaiementDtoResponse>> getAllPaiement();
    public ResponseEntity <PaiementDtoResponse> updatePaiement(Long id, PaiementDtoRequest paiementDtoRequest);
    public String delete(Long id);
}
