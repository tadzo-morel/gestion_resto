package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.ClientDTOResponse;
import com.gestion_restaurant.gestion_restaurant.DTO.ClientRequestDTO;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ClientMapperService clientMapperService;
    @Override
    public ResponseEntity<ClientDTOResponse> create(ClientRequestDTO clientRequestDTO) {
        Client client=new Client();
        client.setNom(clientRequestDTO.getNom());
        client.setPrenom(clientRequestDTO.getPrenom());
        client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        client.setEmail(clientRequestDTO.getEmail());
        client.setLocalisation(clientRequestDTO.getLocalisation());
        client.setPassword(clientRequestDTO.getPassword());
        Client client1=clientRepository.save(client);
        ClientDTOResponse clientDTOResponse=new ClientDTOResponse(
                client1.getNom(),
                client1.getPrenom(),
                client1.getPhoneNumber(),
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
                    client1.getNom(),
                    client1.getPrenom(),
                    client1.getPhoneNumber(),
                    client1.getEmail()
            );

            return new ResponseEntity<>(clientDTOResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<ClientDTOResponse> getAllClient() {
        return clientRepository.findAll().stream()
                .map(clientMapperService::toDTO).toList();
    }

    @Override
    public ResponseEntity<ClientDTOResponse> updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client clientToUpdate = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        clientToUpdate.setNom(clientRequestDTO.getNom());
        clientToUpdate.setEmail(clientRequestDTO.getEmail());
        clientToUpdate.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        clientToUpdate.setPassword(clientRequestDTO.getPassword());
        Client clientNew =clientRepository.save(clientToUpdate);

        ClientDTOResponse clientDTOResponse= new ClientDTOResponse(
                clientNew.getNom(),
                clientNew.getPrenom(),
                clientNew.getEmail(),
                clientNew.getPhoneNumber()
        );
        return new ResponseEntity<>(clientDTOResponse,HttpStatus.OK);
    }

    @Override
    public String delete(Long id) {
        clientRepository.deleteById(id);
        return "Client supprimer";
    }
}
