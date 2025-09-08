package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FactureService {
    public ResponseEntity<FactureDtoResponse> create(FactureDtoRequest factureDtoRequest);
    public ResponseEntity <FactureDtoResponse> getFacture(Long id);
    public ResponseEntity<List<FactureDtoResponse>> getAllFacture();
    public ResponseEntity <FactureDtoResponse> updateFacture(Long id, FactureDtoRequest factureDtoRequest);
    public String delete(Long id);
}
