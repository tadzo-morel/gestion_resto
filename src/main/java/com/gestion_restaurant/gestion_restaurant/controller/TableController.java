package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {
    private final TableService tableService;

    @PostMapping("/create")
    public ResponseEntity<TablesDtoResponse> create(@RequestBody TablesDtoRequest table){
        return tableService.create(table);
    }
    @GetMapping("/")
    public ResponseEntity<List<TablesDtoResponse>> getAllTable(){
        return tableService.getAllTable();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TablesDtoResponse> get(@PathVariable Long id){
        return tableService.getTable(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TablesDtoResponse> update(@PathVariable Long id, @RequestBody TablesDtoRequest table){
        return tableService.updateTable(id,table);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return tableService.delete(id);
    }
}
