package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import com.gestion_restaurant.gestion_restaurant.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    @Override
    public ResponseEntity<ClientDTOResponse> create(ClientRequestDTO clientRequestDTO) {
        Client client=new Client();
        client.setNom(clientRequestDTO.getNom());
        client.setPrenom(clientRequestDTO.getPrenom());
        client.setTelephone(clientRequestDTO.getTelephone());
        client.setEmail(clientRequestDTO.getEmail());
        client.setPassword(clientRequestDTO.getPassword());
        client.setLocalisation(clientRequestDTO.getLocalisation());
        Client client1=clientRepository.save(client);
        ClientDTOResponse clientDTOResponse=new ClientDTOResponse(
                client1.getId(),
                client1.getNom(),
                client1.getPrenom(),
                client1.getTelephone(),
                client1.getLocalisation()
        );
        return new ResponseEntity<>(clientDTOResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDTOResponse> getClient(Long id) {
        Optional<Client> client=clientRepository.findById(id);
        if (client.isPresent()){
            Client client1=client.get();
            ClientDTOResponse clientDTOResponse=new ClientDTOResponse(
                    client1.getId(),
                    client1.getNom(),
                    client1.getPrenom(),
                    client1.getTelephone(),
                    client1.getLocalisation()
            );

            return new ResponseEntity<>(clientDTOResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<ClientDTOResponse>> getAllClient() {
        List<ClientDTOResponse>clientDTOResponses=new ArrayList<>();
        List<Client> clients=clientRepository.findAll();
        for (Client client:clients){
            clientDTOResponses.add(new ClientDTOResponse(
                    client.getId(),
                    client.getNom(),
                    client.getPrenom() ,
                    client.getTelephone(),
                    client.getLocalisation()
            ));
        }
        return new ResponseEntity<>(clientDTOResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDTOResponse> updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client clientToUpdate = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        clientToUpdate.setNom(clientRequestDTO.getNom());
        clientToUpdate.setPrenom(clientRequestDTO.getPrenom());
        clientToUpdate.setTelephone(clientRequestDTO.getTelephone());
        clientToUpdate.setEmail(clientRequestDTO.getEmail());
        clientToUpdate.setPassword(clientRequestDTO.getPassword());
        clientToUpdate.setLocalisation(clientRequestDTO.getLocalisation());
        Client clientNew =clientRepository.save(clientToUpdate);

        ClientDTOResponse clientDTOResponse= new ClientDTOResponse(
                clientNew.getId(),
                clientNew.getNom(),
                clientNew.getPrenom(),
                clientNew.getEmail(),
                clientNew.getTelephone()
        );
        return new ResponseEntity<>(clientDTOResponse,HttpStatus.OK);
    }

    @Override
    public String delete(Long id) {
        clientRepository.deleteById(id);
        return "Client supprimer";
    }

    @Override
    public ResponseEntity<ClientDTOResponse> findByNomClient(String nom) {
       Optional <Client> client1=clientRepository.findByNom(nom);
        if (client1.isPresent()) {
            Client client=client1.get();
        ClientDTOResponse clientDTOResponse=new ClientDTOResponse(
                client.getId(),
                client.getNom(),
                client.getPrenom(),
                client.getTelephone(),
                client.getLocalisation()
        );
        return new ResponseEntity<>(clientDTOResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
