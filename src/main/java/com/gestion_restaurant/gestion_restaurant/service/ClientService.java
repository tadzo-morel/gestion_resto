package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.dto.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.dto.ClientRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    public ResponseEntity <ClientDTOResponse> create(ClientRequestDTO clientRequestDTO);
    public ResponseEntity <ClientDTOResponse> getClient(Long id);
    public ResponseEntity<List<ClientDTOResponse>> getAllClient();
    public ResponseEntity <ClientDTOResponse> updateClient(Long id,ClientRequestDTO clientRequestDTO);
    public String delete(Long id);
    public ResponseEntity<ClientDTOResponse> findByNomClient(String nom);


}
