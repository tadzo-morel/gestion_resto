package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.LigneDeCommandeDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.LigneDeCommande;
import com.gestion_restaurant.gestion_restaurant.repository.LigneCommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {
    private final LigneCommandeRepository ligneCommandeRepository;

    @Override
    public ResponseEntity<LigneDeCommandeDtoResponse> create(LigneDeCommandeDtoRequest ligneDeCommandeDtoRequest) {
        LigneDeCommande ligneDeCommande=new LigneDeCommande();
        ligneDeCommande.setQuantite_article(ligneDeCommandeDtoRequest.quantite_article());
        LigneDeCommande newligneDeCommande=ligneCommandeRepository.save(ligneDeCommande);
        LigneDeCommandeDtoResponse ligneDeCommandeDtoResponse=new LigneDeCommandeDtoResponse(
                newligneDeCommande.getId(),
                newligneDeCommande.getQuantite_article()
        );
        return new ResponseEntity<>(ligneDeCommandeDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneDeCommandeDtoResponse> getLigneCommande(Long id) {
        Optional<LigneDeCommande> ligneDeCommande=ligneCommandeRepository.findById(id);
        if (ligneDeCommande.isPresent()){
            LigneDeCommande ligneDeCommande1=ligneDeCommande.get();
            LigneDeCommandeDtoResponse ligneDeCommandeDtoResponse=new LigneDeCommandeDtoResponse(
                    ligneDeCommande1.getId(),
                    ligneDeCommande1.getQuantite_article()
            );
            return new ResponseEntity<>(ligneDeCommandeDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<LigneDeCommandeDtoResponse>> getAllLigneCommande() {
        List<LigneDeCommande> ligneDeCommandes=ligneCommandeRepository.findAll();
        List<LigneDeCommandeDtoResponse> ligneDeCommandeDtoResponses=new ArrayList<>();
        for (LigneDeCommande ligneDeCommande:ligneDeCommandes){
            LigneDeCommandeDtoResponse ligneDeCommandeDtoResponse=new LigneDeCommandeDtoResponse(
                    ligneDeCommande.getId(),
                    ligneDeCommande.getQuantite_article()
            );
        }
        return new ResponseEntity<>(ligneDeCommandeDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneDeCommandeDtoResponse> updateLigneCommande(Long id, LigneDeCommandeDtoRequest ligneDeCommandeDtoRequest) {
        Optional <LigneDeCommande> ligneDeCommande=ligneCommandeRepository.findById(id);
        if (ligneDeCommande.isPresent()){
            LigneDeCommande ligneDeCommande1=ligneDeCommande.get();
            ligneDeCommande1.setQuantite_article(ligneDeCommandeDtoRequest.quantite_article());
            LigneDeCommande newligneDeCommande=ligneCommandeRepository.save(ligneDeCommande1);
            LigneDeCommandeDtoResponse ligneDeCommandeDtoResponse=new LigneDeCommandeDtoResponse(
                    newligneDeCommande.getId(),
                    newligneDeCommande.getQuantite_article()
            );
            return new ResponseEntity<>(ligneDeCommandeDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        ligneCommandeRepository.deleteById(id);
        return "Ligne de commande Supprimer";
    }
}
