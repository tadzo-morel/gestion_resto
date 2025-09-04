package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.repository.ClientRepository;
import com.gestion_restaurant.gestion_restaurant.service.ClientMapperService;
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
    private final ClientRepository clientRepository;
    private final ClientMapperService clientMapperService;
    @PostMapping("/create")
    public ResponseEntity<ClientDTOResponse> create(@RequestBody ClientRequestDTO client){
        return clientService.create(client);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity <ClientDTOResponse> getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity <ClientDTOResponse> updateUser(@PathVariable Long id,@RequestBody ClientRequestDTO client){
        return clientService.updateClient(id,client);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return clientService.delete(id);
    }
    @GetMapping("/")
    public List<ClientDTOResponse> getAllClient(){
        return  clientService.getAllClient();
    }
//    @GetMapping("/email/{nom}")
//    public String getEmailByName( @PathVariable String nom){
//        return clientService.getEmailByName(nom);
//    }
}
