package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.dto.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.dto.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.dto.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import com.gestion_restaurant.gestion_restaurant.repository.SalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SalleServiceImpl implements SalleService {
    
    private final SalleRepository salleRepository;

    // Added constructor to initialize salleRepository
    // public SalleServiceImpl(SalleRepository salleRepository) {
    //     this.salleRepository = salleRepository;
    // }

    @Override
    public ResponseEntity<SalleDtoResponse> create(SalleDtoRequest salleDtoRequest) {
        Salle salle = new Salle();
        salle.setCapacite(salleDtoRequest.capacite());
        salle.setStatus(salleDtoRequest.status());
        
        Salle newSalle = salleRepository.save(salle);
        
        SalleDtoResponse response = new SalleDtoResponse(
                newSalle.getId(),
                newSalle.getCapacite(),
                newSalle.getStatus(),
                null,
                newSalle.getTables().size(),
                newSalle.getTables().stream()
                        .map(t -> new TablesDtoResponse(
                                t.getId(),
                                t.getStatus(),
                                t.getNbrePlace(),
                                t.getSalle().getId(),
                                "Salle " + t.getSalle().getId()
                        ))
                        .collect(Collectors.toList())
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> getSalle(Long id) {
        Optional<Salle> salleOpt = salleRepository.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Salle not found"));
        
        if (salleOpt.isPresent()) {
            Salle salle = salleOpt.get();
            
            SalleDtoResponse response = new SalleDtoResponse(
                    salle.getId(),
                    salle.getCapacite(),
                    salle.getStatus(),
                    null,
                    salle.getTables().size(),
                    salle.getTables().stream()
                            .map(t -> new TablesDtoResponse(
                                    t.getId(),
                                    t.getStatus(),
                                    t.getNbrePlace(),
                                    t.getSalle().getId(),
                                    "Salle " + t.getSalle().getId()
                            ))
                            .collect(Collectors.toList())
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        throw new EntityNotFoundException("Salle not found");
    }

    @Override
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle() {
        List<Salle> salles = salleRepository.findAll();
        List<SalleDtoResponse> responses = new ArrayList<>();
        
        for (Salle salle : salles) {
            responses.add(new SalleDtoResponse(
                    salle.getId(),
                    salle.getCapacite(),
                    salle.getStatus(),
                    null,
                    salle.getTables().size(),
                    salle.getTables().stream()
                            .map(t -> new TablesDtoResponse(
                                    t.getId(),
                                    t.getStatus(),
                                    t.getNbrePlace(),
                                    t.getSalle().getId(),
                                    "Salle " + t.getSalle().getId()
                            ))
                            .collect(Collectors.toList())
            ));
        }
        
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> updateSalle(Long id, SalleDtoRequest salleDtoRequest) {
        Optional<Salle> salleOpt = salleRepository.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Salle not found"));
        
        if (salleOpt.isPresent()) {
            Salle salle = salleOpt.get();
            salle.setCapacite(salleDtoRequest.capacite());
            salle.setStatus(salleDtoRequest.status());
            
            Salle updatedSalle = salleRepository.save(salle);
            
            SalleDtoResponse response = new SalleDtoResponse(
                    updatedSalle.getId(),
                    updatedSalle.getCapacite(),
                    updatedSalle.getStatus(),
                    null,
                    updatedSalle.getTables().size(),
                    updatedSalle.getTables().stream()
                            .map(t -> new TablesDtoResponse(
                                    t.getId(),
                                    t.getStatus(),
                                    t.getNbrePlace(),
                                    t.getSalle().getId(),
                                    "Salle " + t.getSalle().getId()
                            ))
                            .collect(Collectors.toList())
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
        
        if (!salle.getTables().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer. La salle contient des tables.");
        }
        
        salleRepository.deleteById(id);
        return "Salle supprimée avec succès";
    }
}