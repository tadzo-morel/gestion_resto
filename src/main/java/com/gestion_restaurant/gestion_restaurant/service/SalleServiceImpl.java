package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import com.gestion_restaurant.gestion_restaurant.entity.Tables;
import com.gestion_restaurant.gestion_restaurant.repository.SalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SalleServiceImpl implements SalleService{
    private final SalleRepository salleRepository;

    @Override
    public ResponseEntity<SalleDtoResponse> create(SalleDtoRequest salleDtoRequest) {
        Salle salle=new Salle();
        salle.setStatus(salleDtoRequest.status());
        salle.setCapacite(salleDtoRequest.capacite());
        Salle newSalle=salleRepository.save(salle);
        SalleDtoResponse salleDtoResponse=new SalleDtoResponse(
                newSalle.getId(),
                newSalle.getStatus(),
                newSalle.getCapacite()
        );

        return new ResponseEntity<>(salleDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> getSalle(Long id) {
        Optional<Salle>salle=salleRepository.findById(id);
        if (salle.isPresent()){
            Salle newSalle=salle.get();
            SalleDtoResponse salleDtoResponse=new SalleDtoResponse(
                    newSalle.getId(),
                    newSalle.getStatus(),
                    newSalle.getCapacite()
            );
            return new ResponseEntity<>(salleDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle() {
       List<Salle>salles=salleRepository.findAll();
       List<SalleDtoResponse>salleDtoResponses=new ArrayList<>();
       for (Salle salle:salles){
           salleDtoResponses.add(new SalleDtoResponse(
                   salle.getId(),
                   salle.getStatus(),
                   salle.getCapacite()
           ));
       }
        return new ResponseEntity<>(salleDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> updateTable(Long id, SalleDtoRequest salleDtoRequest) {
        Optional<Salle>salle=salleRepository.findById(id);
        if (salle.isPresent()){
            Salle salle1=salle.get();
            salle1.setStatus(salleDtoRequest.status());
            salle1.setCapacite(salleDtoRequest.capacite());
            Salle newSalle=salleRepository.save(salle1);
            SalleDtoResponse salleDtoResponse=new SalleDtoResponse(
                    newSalle.getId(),
                    newSalle.getStatus(),
                    newSalle.getCapacite()
            );
            return new ResponseEntity<>(salleDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        salleRepository.deleteById(id);
        return "Salle supprimer";
    }
}
