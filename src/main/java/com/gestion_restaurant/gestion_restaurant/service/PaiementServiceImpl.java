package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.PaiementDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Paiement;
import com.gestion_restaurant.gestion_restaurant.repository.PaiementRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaiementServiceImpl implements PaiementService{
    private final PaiementRepository paiementRepository;
    @Override
    public ResponseEntity<PaiementDtoResponse> create(PaiementDtoRequest paiementDtoRequest) {
        Paiement paiement=new Paiement();
        paiement.setDate_paiement(paiementDtoRequest.date_paiement());
        paiement.setHeure_paiement(paiementDtoRequest.heure_paiement());
        paiement.setMontant(paiementDtoRequest.montant());
        paiement.setMode(paiementDtoRequest.mode());
        Paiement newPaiement=paiementRepository.save(paiement);
        PaiementDtoResponse paiementDtoResponse=new PaiementDtoResponse(
                newPaiement.getId(),
                newPaiement.getDate_paiement(),
                newPaiement.getHeure_paiement(),
                newPaiement.getMontant(),
                newPaiement.getMode()
        );
        return new ResponseEntity<>(paiementDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaiementDtoResponse> getPaiement(Long id) {
        Optional<Paiement>paiement=paiementRepository.findById(id);
        if (paiement.isPresent()){
            Paiement paiement1=paiement.get();
            PaiementDtoResponse paiementDtoResponse=new PaiementDtoResponse(
                    paiement1.getId(),
                    paiement1.getDate_paiement(),
                    paiement1.getHeure_paiement(),
                    paiement1.getMontant(),
                    paiement1.getMode()
            );
            return new ResponseEntity<>(paiementDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<PaiementDtoResponse>> getAllPaiement() {
        List<Paiement>paiements=paiementRepository.findAll();
        List<PaiementDtoResponse>paiementDtoResponses=new ArrayList<>();
        for (Paiement paiement:paiements){
            paiementDtoResponses.add(new PaiementDtoResponse(
                    paiement.getId(),
                    paiement.getDate_paiement(),
                    paiement.getHeure_paiement(),
                    paiement.getMontant(),
                    paiement.getMode()
            ));
        }
        return new ResponseEntity<>(paiementDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaiementDtoResponse> updatePaiement(Long id, PaiementDtoRequest paiementDtoRequest) {
        Optional<Paiement>paiement=paiementRepository.findById(id);
        if (paiement.isPresent()){
            Paiement setPaiement=paiement.get();
            setPaiement.setDate_paiement(paiementDtoRequest.date_paiement());
            setPaiement.setHeure_paiement(paiementDtoRequest.heure_paiement());
            setPaiement.setMontant(paiementDtoRequest.montant());
            setPaiement.setMode(paiementDtoRequest.mode());
            Paiement newPaiement=paiementRepository.save(setPaiement);
            PaiementDtoResponse paiementDtoResponse=new PaiementDtoResponse(
                    newPaiement.getId(),
                    newPaiement.getDate_paiement(),
                    newPaiement.getHeure_paiement(),
                    newPaiement.getMontant(),
                    newPaiement.getMode()
            );
            return new ResponseEntity<>(paiementDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        paiementRepository.deleteById(id);
        return "Paiement annuler";
    }
}
