package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.dto.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.dto.SalleDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalleService {
    public ResponseEntity<SalleDtoResponse> create(SalleDtoRequest salleDtoRequest);
    public ResponseEntity <SalleDtoResponse> getSalle(Long id);
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle();
    public ResponseEntity <SalleDtoResponse> updateSalle(Long id, SalleDtoRequest salleDtoRequest);
    public String delete(Long id);
}
