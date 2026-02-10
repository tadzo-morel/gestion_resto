package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.dto.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.dto.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import com.gestion_restaurant.gestion_restaurant.repository.SalleRepository;
import com.gestion_restaurant.gestion_restaurant.repository.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final SalleRepository salleRepository;

    // Added constructor to initialize tableRepository and salleRepository
    // public TableServiceImpl(TableRepository tableRepository, SalleRepository salleRepository) {
    //     this.tableRepository = tableRepository;
    //     this.salleRepository = salleRepository;
    // }

    @Override
    public ResponseEntity<TablesDtoResponse> create(TablesDtoRequest tablesDtoRequest) {
        Salle salle = null;
        if (tablesDtoRequest.salleId() != null) {
            salle = salleRepository.findById(tablesDtoRequest.salleId())
                    .orElseThrow(() -> new IllegalArgumentException("Salle not found"));
        }

        Tables tables = new Tables();
        tables.setNbrePlace(tablesDtoRequest.nbrePlace());
        tables.setStatus(tablesDtoRequest.status());
        tables.setSalle(salle);

        if (salle != null) {
            salle.addTable(tables);
        }

        Tables newTable = tableRepository.save(tables);
        TablesDtoResponse tablesDtoResponse = new TablesDtoResponse(
                newTable.getId(),
                newTable.getStatus(),
                newTable.getNbrePlace(),
                newTable.getSalle() != null ? newTable.getSalle().getId() : null,
                newTable.getSalle() != null ? "Salle " + newTable.getSalle().getId() : "Aucune salle"
        );
        return new ResponseEntity<>(tablesDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TablesDtoResponse> getTable(Long id) {
        Optional<Tables> optionalTable = tableRepository.findById(id);
        if (optionalTable.isEmpty()) {
            throw new IllegalArgumentException("Table not found");
        }
        Tables table = optionalTable.get();

        TablesDtoResponse tablesDtoResponse = new TablesDtoResponse(
                table.getId(),
                table.getStatus(),
                table.getNbrePlace(),
                table.getSalle() != null ? table.getSalle().getId() : null,
                table.getSalle() != null ? "Salle " + table.getSalle().getId() : "Aucune salle"
        );
        return new ResponseEntity<>(tablesDtoResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TablesDtoResponse>> getAllTable() {
        List<Tables> tables = tableRepository.findAll();
        List<TablesDtoResponse> tablesDtoResponses = new ArrayList<>();
        for (Tables table : tables) {
            tablesDtoResponses.add(new TablesDtoResponse(
                    table.getId(),
                    table.getStatus(),
                    table.getNbrePlace(),
                    table.getSalle() != null ? table.getSalle().getId() : null,
                    table.getSalle() != null ? "Salle " + table.getSalle().getId() : "Aucune salle"
            ));
        }
        return new ResponseEntity<>(tablesDtoResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TablesDtoResponse> updateTable(Long id, TablesDtoRequest tablesDtoRequest) {
        Optional<Tables> optionalTable = tableRepository.findById(id);
        if (optionalTable.isEmpty()) {
            throw new IllegalArgumentException("Tables not found");
        }
        Tables table = optionalTable.get();

        table.setStatus(tablesDtoRequest.status());
        table.setNbrePlace(tablesDtoRequest.nbrePlace());

        if (tablesDtoRequest.salleId() != null) {
            Optional<Salle> optionalSalle = salleRepository.findById(tablesDtoRequest.salleId());
            if (optionalSalle.isEmpty()) {
                throw new IllegalArgumentException("Salle not found");
            }
            Salle nouvelleSalle = optionalSalle.get();

            if (table.getSalle() != null && !table.getSalle().getId().equals(nouvelleSalle.getId())) {
                table.getSalle().getTables().remove(table);
            }

            table.setSalle(nouvelleSalle);
            nouvelleSalle.addTable(table);
        } else {
            if (table.getSalle() != null) {
                table.getSalle().getTables().remove(table);
            }
            table.setSalle(null);
        }

        Tables newTable = tableRepository.save(table);
        TablesDtoResponse tablesDtoResponse = new TablesDtoResponse(
                newTable.getId(),
                newTable.getStatus(),
                newTable.getNbrePlace(),
                newTable.getSalle() != null ? newTable.getSalle().getId() : null,
                newTable.getSalle() != null ? "Salle " + newTable.getSalle().getId() : "Aucune salle"
        );
        return new ResponseEntity<>(tablesDtoResponse, HttpStatus.OK);
    }

    @Override
    public String delete(Long id) {
        tableRepository.deleteById(id);
        return "Table Supprimer";
    }
    
    public ResponseEntity<List<TablesDtoResponse>> getTablesBySalle(Long salleId) {
        List<Tables> tables = tableRepository.findBySalleId(salleId);
        List<TablesDtoResponse> responses = new ArrayList<>();
        
        for (Tables table : tables) {
            responses.add(new TablesDtoResponse(
                    table.getId(),
                    table.getStatus(),
                    table.getNbrePlace(),
                    table.getSalle().getId(),
                    "Salle " + table.getSalle().getId()
            ));
        }
        
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    
    public ResponseEntity<TablesDtoResponse> assignTableToSalle(Long tableId, Long salleId) {
        Tables table = tableRepository.findById(tableId).orElseThrow(() -> new IllegalArgumentException("Table not found"));
        
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new IllegalArgumentException("Salle not found"));
        
        if (table.getSalle() != null) {
            table.getSalle().getTables().remove(table);
        }
        
        table.setSalle(salle);
        salle.addTable(table);
        
        Tables updatedTable = tableRepository.save(table);
        
        TablesDtoResponse response = new TablesDtoResponse(
                updatedTable.getId(),
                updatedTable.getStatus(),
                updatedTable.getNbrePlace(),
                salle.getId(),
                "Salle " + salle.getId()
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    public ResponseEntity<TablesDtoResponse> removeTableFromSalle(Long tableId) {
        Tables table = tableRepository.findById(tableId).orElseThrow(() -> new IllegalArgumentException("Table not found"));
        
        if (table.getSalle() == null) {
            throw new RuntimeException("La table n'est pas assignée à une salle");
        }
        
        table.getSalle().getTables().remove(table);
        table.setSalle(null);
        
        Tables updatedTable = tableRepository.save(table);
        
        TablesDtoResponse response = new TablesDtoResponse(
                updatedTable.getId(),
                updatedTable.getStatus(),
                updatedTable.getNbrePlace(),
                null,
                "Aucune salle"
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
