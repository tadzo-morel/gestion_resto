package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapperService {
    public ClientDTOResponse toDTO(Client client){
        return new ClientDTOResponse(
                client.getNom(),
                client.getPrenom() ,
                client.getPhoneNumber(),
                client.getLocalisation()
        );
    }
}
