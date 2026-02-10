package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.dto.CommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.dto.CommandeDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommandeService {
    public ResponseEntity<CommandeDtoResponse> create(CommandeDtoRequest commandeDtoRequest);
    public ResponseEntity <CommandeDtoResponse> getCommande(Long id);
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommande();
    public ResponseEntity <CommandeDtoResponse> updateCommande(Long id,CommandeDtoRequest commandeDtoRequest);
    public String delete(Long id);
    public ResponseEntity <List<CommandeDtoResponse>> getAllCommandeFromClient(String nom);
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommandeFromLivreur(String nom);
}
