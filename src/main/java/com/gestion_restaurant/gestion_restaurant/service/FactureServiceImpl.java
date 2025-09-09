package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.FactureDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Facture;
import com.gestion_restaurant.gestion_restaurant.repository.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;

    @Override
    public ResponseEntity<FactureDtoResponse> create(FactureDtoRequest factureDtoRequest) {
        Facture facture=new Facture();
        facture.setNum_compte(factureDtoRequest.num_compte());
        Facture newFacture=factureRepository.save(facture);
        FactureDtoResponse factureDtoResponse=new FactureDtoResponse(
                newFacture.getId(),
                newFacture.getNum_compte()
        );
        return new ResponseEntity<>(factureDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<FactureDtoResponse> getFacture(Long id) {
        Optional<Facture> facture=factureRepository.findById(id);
        if (facture.isPresent()){
            Facture facture1=facture.get();
            FactureDtoResponse factureDtoResponse=new FactureDtoResponse(
                    facture1.getId(),
                    facture1.getNum_compte()
            );
            return new ResponseEntity<>(factureDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<FactureDtoResponse>> getAllFacture() {
        List<Facture>factures=factureRepository.findAll();
        List<FactureDtoResponse> factureDtoResponses=new ArrayList<>();
        for (Facture facture:factures){
            factureDtoResponses.add(new FactureDtoResponse(
                    facture.getId(),
                    facture.getNum_compte()
            ));
        }
        return new ResponseEntity<>(factureDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FactureDtoResponse> updateFacture(Long id, FactureDtoRequest factureDtoRequest) {
        Optional<Facture>facture=factureRepository.findById(id);
        if (facture.isPresent()){
            Facture facture1=facture.get();
            facture1.setNum_compte(factureDtoRequest.num_compte());
            Facture newFacture=factureRepository.save(facture1);
            FactureDtoResponse factureDtoResponse=new FactureDtoResponse(
                    newFacture.getId(),
                    newFacture.getNum_compte()
            );
            return new ResponseEntity<>(factureDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        factureRepository.deleteById(id);
        return "Facture supprimer";
    }
}
