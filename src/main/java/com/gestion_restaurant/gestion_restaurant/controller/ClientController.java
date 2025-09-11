package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import com.gestion_restaurant.gestion_restaurant.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    @PostMapping("/create")
    public ResponseEntity<ClientDTOResponse> create(@RequestBody ClientRequestDTO client){
        return clientService.create(client);
    }
    @GetMapping("/")
    public ResponseEntity<List<ClientDTOResponse>> getAllClient(){
        return  clientService.getAllClient();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity <ClientDTOResponse> getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity <ClientDTOResponse> updateUser(@PathVariable Long id,@RequestBody ClientRequestDTO client){
        return clientService.updateClient(id,client);
    }
    @GetMapping("/{nom}")
    public Client getByNomClient(@PathVariable String nom){
        return clientService.findByNomClient(nom);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return clientService.delete(id);
    }

}
