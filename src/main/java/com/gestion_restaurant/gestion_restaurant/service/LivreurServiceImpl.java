package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import com.gestion_restaurant.gestion_restaurant.repository.LivreurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivreurServiceImpl implements LivreurService{
    private final LivreurRepository livreurRepository;
    private final LivreurMapperService livreurMapperService;
    @Override
    public ResponseEntity<LivreurDtoResponse> create(LivreurDtoRequest livreurDtoRequest) {
        Livreur livreur=new Livreur();
        livreur.setNom(livreurDtoRequest.nom());
        livreur.setPrenom(livreurDtoRequest.prenom());
        livreur.setPhoneNumber(livreurDtoRequest.phoneNumber());
        livreur.setEmail(livreurDtoRequest.email());
        livreur.setStatus(livreurDtoRequest.status());
        livreur.setPassword(livreurDtoRequest.password());
        Livreur newLivreur=livreurRepository.save(livreur);
        LivreurDtoResponse livreurDtoResponse=new LivreurDtoResponse(
                newLivreur.getNom(),
                newLivreur.getPrenom(),
                newLivreur.getPhoneNumber(),
                newLivreur.getStatus()
        );

        return new ResponseEntity<>(livreurDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LivreurDtoResponse> getLivreur(Long id) {
        return null;
    }

    @Override
    public List<LivreurDtoResponse> getAllLivreur() {
        return List.of();
    }

    @Override
    public ResponseEntity<LivreurDtoResponse> updateLivreur(Long id, LivreurDtoRequest livreurDtoRequest) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return "";
    }
}
