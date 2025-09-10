package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import com.gestion_restaurant.gestion_restaurant.service.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "commande")
public class CommandeController {
    private final CommandeService commandeService;
    @PostMapping("/create")
    public ResponseEntity<CommandeDtoResponse> create(@RequestBody CommandeDtoRequest commande){
        return commandeService.create(commande);
    }
    @GetMapping("/")
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommande(){
        return commandeService.getAllCommande();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDtoResponse> getCommande(@PathVariable Long id){
        return commandeService.getCommande(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CommandeDtoResponse> update(@PathVariable Long id, @RequestBody CommandeDtoRequest commande){
        return commandeService.updateCommande(id,commande);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return commandeService.delete(id);
    }
    @GetMapping("/nomClient/{nom}")
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommandeBYClient(@PathVariable String nom){
        return commandeService.getAllCommandeFromClient(nom);
    }
    @GetMapping("/nomLivreur/{nom}")
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommandeBYLivreur(@PathVariable String nom){
        return commandeService.getAllCommandeFromLivreur(nom);
    }
}
