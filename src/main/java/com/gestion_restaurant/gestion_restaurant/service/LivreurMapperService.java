package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.LivreurDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import org.springframework.stereotype.Service;

@Service
public class LivreurMapperService {
    public LivreurDtoResponse toDTO(Livreur livreur){
        return new LivreurDtoResponse(
                livreur.getNom(),
                livreur.getPrenom(),
                livreur.getPhoneNumber(),
                livreur.getStatus()
        );
    }
}
