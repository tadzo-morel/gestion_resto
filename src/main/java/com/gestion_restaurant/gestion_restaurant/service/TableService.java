package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TableService {
    public ResponseEntity<TablesDtoResponse> create(TablesDtoRequest tablesDtoRequest);
    public ResponseEntity <TablesDtoResponse> getTable(Long id);
    public ResponseEntity<List<TablesDtoResponse>> getAllTable();
    public ResponseEntity <TablesDtoResponse> updateTable(Long id, TablesDtoRequest tablesDtoRequest);
    public String delete(Long id);
}
