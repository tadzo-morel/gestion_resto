package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalleService {
    public ResponseEntity<SalleDtoResponse> create(SalleDtoRequest salleDtoRequest);
    public ResponseEntity <SalleDtoResponse> getSalle(Long id);
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle();
    public ResponseEntity <SalleDtoResponse> updateTable(Long id, SalleDtoRequest salleDtoRequest);
    public String delete(Long id);
}
