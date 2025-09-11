package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import com.gestion_restaurant.gestion_restaurant.repository.LivreurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivreurServiceImpl implements LivreurService{
    private final LivreurRepository livreurRepository;
    @Override
    public ResponseEntity<LivreurDtoResponse> create(LivreurDtoRequest livreurDtoRequest) {
        Livreur livreur=new Livreur();
        livreur.setNom(livreurDtoRequest.nom());
        livreur.setPrenom(livreurDtoRequest.prenom());
        livreur.setTelephone(livreurDtoRequest.telephone());
        livreur.setEmail(livreurDtoRequest.email());
        livreur.setStatus(livreurDtoRequest.status());
        livreur.setPassword(livreurDtoRequest.password());
        Livreur newLivreur=livreurRepository.save(livreur);
        LivreurDtoResponse livreurDtoResponse=new LivreurDtoResponse(
                newLivreur.getId(),
                newLivreur.getNom(),
                newLivreur.getPrenom(),
                newLivreur.getTelephone(),
                newLivreur.getStatus()
        );

        return new ResponseEntity<>(livreurDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LivreurDtoResponse> getLivreur(Long id) {
        Optional<Livreur>livreur=livreurRepository.findById(id);
        if (livreur.isPresent()){
            Livreur livreur1=livreur.get();
            LivreurDtoResponse livreurDtoResponse=new LivreurDtoResponse(
                    livreur1.getId(),
                    livreur1.getNom(),
                    livreur1.getPrenom(),
                    livreur1.getTelephone(),
                    livreur1.getStatus()
            );
            return new ResponseEntity<>(livreurDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<LivreurDtoResponse>> getAllLivreur() {
        List<LivreurDtoResponse>livreurDtoResponses=new ArrayList<>();
        List<Livreur>livreurs=livreurRepository.findAll();
        for (Livreur livreur:livreurs){
            livreurDtoResponses.add(new LivreurDtoResponse(
                            livreur.getId(),
                            livreur.getNom(),
                            livreur.getPrenom(),
                            livreur.getTelephone(),
                            livreur.getStatus()
                    ));
        }

        return new ResponseEntity<>(livreurDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LivreurDtoResponse> updateLivreur(Long id, LivreurDtoRequest livreurDtoRequest) {
        Optional<Livreur>livreur=livreurRepository.findById(id);
        if (livreur.isPresent()){
            Livreur livreur1=livreur.get();
            livreur1.setNom(livreurDtoRequest.nom());
            livreur1.setPrenom(livreurDtoRequest.prenom());
            livreur1.setTelephone(livreurDtoRequest.telephone());
            livreur1.setEmail(livreurDtoRequest.email());
            livreur1.setStatus(livreurDtoRequest.status());
            livreur1.setPassword(livreurDtoRequest.password());
            Livreur newLivreur=livreurRepository.save(livreur1);
            LivreurDtoResponse livreurDtoResponse=new LivreurDtoResponse(
                    newLivreur.getId(),
                    newLivreur.getNom(),
                    newLivreur.getPrenom(),
                    newLivreur.getTelephone(),
                    newLivreur.getStatus()
            );
            return new ResponseEntity<>(livreurDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        livreurRepository.deleteById(id);
        return "Livreur supprimer";
    }

    @Override
    public ResponseEntity<LivreurDtoResponse> findByName(String nom) {
       Optional <Livreur> livreur=livreurRepository.findByNom(nom);
       if (livreur.isPresent()){
           Livreur livreur1=livreur.get();
           LivreurDtoResponse livreurDtoResponse=new LivreurDtoResponse(
                   livreur1.getId(),
                   livreur1.getNom(),
                   livreur1.getPrenom(),
                   livreur1.getTelephone(),
                   livreur1.getStatus()
           );
           return new ResponseEntity<>(livreurDtoResponse,HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
