package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.SalleDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import com.gestion_restaurant.gestion_restaurant.entity.StatusEspace;
import com.gestion_restaurant.gestion_restaurant.repository.SalleRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoRequest;
import com.gestion_restaurant.gestion_restaurant.entity.Tables;

@Service
@AllArgsConstructor
@Transactional
public class SalleServiceImpl implements SalleService {
    
    private final SalleRepository salleRepository;

    @Override
    public ResponseEntity<SalleDtoResponse> create(SalleDtoRequest salleDtoRequest) {
        Salle salle = new Salle();
        salle.setCapacite(Long.valueOf(salleDtoRequest.capacite()));
        salle.setStatus(salleDtoRequest.status());
        
        // CORRECTION: Ne pas lier une salle à une réservation ici
        // Une salle peut avoir plusieurs réservations, pas une seule
        // salle.setReservation(...) -> À SUPPRIMER
        
        Salle newSalle = salleRepository.save(salle);
        
        SalleDtoResponse salleDtoResponse = new SalleDtoResponse(
                newSalle.getId(),
                newSalle.getCapacite(),
                newSalle.getStatus(),
                newSalle.getTables().size() // Nombre de tables dans la salle
        );
        
        return new ResponseEntity<>(salleDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> getSalle(Long id) {
        Optional<Salle> salle = salleRepository.findById(id);
        
        if (salle.isPresent()) {
            Salle newSalle = salle.get();
            
            SalleDtoResponse salleDtoResponse = new SalleDtoResponse(
                    newSalle.getId(),
                    newSalle.getCapacite(),
                    newSalle.getStatus(),
                    newSalle.getTables().size() // Nombre de tables
            );
            
            return new ResponseEntity<>(salleDtoResponse, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<SalleDtoResponse>> getAllSalle() {
        List<Salle> salles = salleRepository.findAll();
        List<SalleDtoResponse> salleDtoResponses = new ArrayList<>();
        
        for (Salle salle : salles) {
            salleDtoResponses.add(new SalleDtoResponse(
                    salle.getId(),
                    salle.getCapacite(),
                    salle.getStatus(),
                    salle.getTables().size() // Nombre de tables
            ));
        }
        
        return new ResponseEntity<>(salleDtoResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalleDtoResponse> updateSalle(Long id, SalleDtoRequest salleDtoRequest) {
        Optional<Salle> salle = salleRepository.findById(id);
        
        if (salle.isPresent()) {
            Salle salle1 = salle.get();
            salle1.setStatus(salleDtoRequest.status());
            salle1.setCapacite(Long.valueOf(salleDtoRequest.capacite()));
            
            // CORRECTION: Ne pas modifier la relation avec réservation
            // salle1.setReservation(...) -> À SUPPRIMER
            
            Salle updatedSalle = salleRepository.save(salle1);
            
            SalleDtoResponse salleDtoResponse = new SalleDtoResponse(
                    updatedSalle.getId(),
                    updatedSalle.getCapacite(),
                    updatedSalle.getStatus(),
                    updatedSalle.getTables().size()
            );
            
            return new ResponseEntity<>(salleDtoResponse, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        // Vérifier si la salle existe
        if (salleRepository.existsById(id)) {
            // Vérifier si la salle a des tables
            Salle salle = salleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
            
            if (!salle.getTables().isEmpty()) {
                throw new RuntimeException("Impossible de supprimer la salle. Elle contient des tables.");
            }
            
            if (salle.getReservation() != null) {
                throw new RuntimeException("Impossible de supprimer la salle. Elle a des réservations.");
            }
            
            salleRepository.deleteById(id);
            return "Salle supprimée avec succès";
        }
        
        throw new RuntimeException("Salle non trouvée avec l'ID: " + id);
    }
    
    // AJOUT: Méthodes supplémentaires utiles
    public ResponseEntity<List<SalleDtoResponse>> getSallesDisponibles() {
        List<Salle> salles = salleRepository.findSallesDisponibles(StatusEspace.disponible);
        List<SalleDtoResponse> responses = new ArrayList<>();
        
        for (Salle salle : salles) {
            responses.add(new SalleDtoResponse(
                    salle.getId(),
                    salle.getCapacite(),
                    salle.getStatus(),
                    salle.getTables().size()
            ));
        }
        
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    
    public ResponseEntity<SalleDtoResponse> addTableToSalle(Long salleId, TablesDtoRequest tableRequest) {
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
        
        Tables table = new Tables();
        table.setNbre_place(tableRequest.nbre_place());
        table.setStatus(tableRequest.status());
        table.setSalle(salle);
        
        salle.getTables().add(table);
        Salle updatedSalle = salleRepository.save(salle);
        
        SalleDtoResponse response = new SalleDtoResponse(
                updatedSalle.getId(),
                updatedSalle.getCapacite(),
                updatedSalle.getStatus(),
                updatedSalle.getTables().size()
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}