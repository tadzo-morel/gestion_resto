package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import com.gestion_restaurant.gestion_restaurant.repository.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TableServiceImpl implements TableService{
    private final TableRepository tableRepository;

    @Override
    public ResponseEntity<TablesDtoResponse> create(TablesDtoRequest tablesDtoRequest) {
        Tables tables=new Tables();
        tables.setNbre_place(tablesDtoRequest.nbre_place());
        tables.setStatus(tablesDtoRequest.status());
        Tables newTable=tableRepository.save(tables);
        TablesDtoResponse tablesDtoResponse =new TablesDtoResponse(
                newTable.getId(),
                newTable.getStatus(),
                newTable.getNbre_place()
        );
        return new ResponseEntity<>(tablesDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TablesDtoResponse> getTable(Long id) {
        Optional<Tables>tables=tableRepository.findById(id);
        if (tables.isPresent()){
            Tables newTable=tables.get();
            TablesDtoResponse tablesDtoResponse=new TablesDtoResponse(
                    newTable.getId(),
                    newTable.getStatus(),
                    newTable.getNbre_place()
            );
            return new ResponseEntity<>(tablesDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<TablesDtoResponse>> getAllTable() {
        List<Tables> tables=tableRepository.findAll();
        List<TablesDtoResponse>tablesDtoResponses=new ArrayList<>();
        for (Tables table:tables){
            tablesDtoResponses.add(new TablesDtoResponse(
                    table.getId(),
                    table.getStatus(),
                    table.getNbre_place()
            ));
        }
        return new ResponseEntity<>(tablesDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TablesDtoResponse> updateTable(Long id, TablesDtoRequest tablesDtoRequest) {
        Optional <Tables> tables=tableRepository.findById(id);
        if (tables.isPresent()){
            Tables table=tables.get();
            table.setStatus(tablesDtoRequest.status());
            table.setNbre_place(tablesDtoRequest.nbre_place());
            Tables newTable=tableRepository.save(table);
            TablesDtoResponse tablesDtoResponse=new TablesDtoResponse(
                    newTable.getId(),
                    newTable.getStatus(),
                    newTable.getNbre_place()
            );
            return new ResponseEntity<>(tablesDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        tableRepository.deleteById(id);
        return "Table Supprimer";
    }
}
