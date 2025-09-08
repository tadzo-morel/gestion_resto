package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommandeService {
    public ResponseEntity<CommandeDtoResponse> create(CommandeDtoRequest commandeDtoRequest);
    public ResponseEntity <CommandeDtoResponse> getCommande(Long id);
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommande();
    public ResponseEntity <CommandeDtoResponse> updateCommande(Long id,CommandeDtoRequest commandeDtoRequest);
    public String delete(Long id);


}
