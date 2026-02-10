package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.dto.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.TableServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {

    private final TableServiceImpl tableService;

    @GetMapping("/salle/{salleId}") // Endpoint pour récupérer les tables d'une salle spécifique
    public ResponseEntity<List<TablesDtoResponse>> getTablesBySalle(@PathVariable Long salleId) {
        return tableService.getTablesBySalle(salleId);
    }
    
    @PostMapping("/{tableId}/assign-salle/{salleId}") // Endpoint pour assigner une table à une salle
    public ResponseEntity<TablesDtoResponse> assignToSalle(@PathVariable Long tableId, 
                                                          @PathVariable Long salleId) {
        return tableService.assignTableToSalle(tableId, salleId);
    }
    
    @PostMapping("/{tableId}/remove-salle") // Endpoint pour retirer une table d'une salle
    public ResponseEntity<TablesDtoResponse> removeFromSalle(@PathVariable Long tableId) {
        return tableService.removeTableFromSalle(tableId);
    }
}