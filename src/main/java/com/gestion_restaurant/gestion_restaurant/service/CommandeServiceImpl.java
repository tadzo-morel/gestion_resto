package com.gestion_restaurant.gestion_restaurant.service;


import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.CommandeDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Client;
import com.gestion_restaurant.gestion_restaurant.entity.Commande;
import com.gestion_restaurant.gestion_restaurant.entity.Livreur;
import com.gestion_restaurant.gestion_restaurant.repository.ClientRepository;
import com.gestion_restaurant.gestion_restaurant.repository.CommandeRepository;
import com.gestion_restaurant.gestion_restaurant.repository.LivreurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommandeServiceImpl implements CommandeService{
    private final CommandeRepository commandeRepository;
    private final LivreurRepository livreurRepository;
    private final ClientRepository clientRepository;
    @Override
    public ResponseEntity<CommandeDtoResponse> create(CommandeDtoRequest commandeDtoRequest) {
        Commande commande=new Commande();
        commande.setDateCommande(commandeDtoRequest.dateCommande());
        commande.setHeureCommande(commandeDtoRequest.heureCommande());
        commande.setDateLivraison(commandeDtoRequest.dateLivraison());
        commande.setHeureLivraison(commandeDtoRequest.heureLivraison());
        commande.setMontant(commandeDtoRequest.montant());
        commande.setStatus(commandeDtoRequest.status());
        commande.setLocalisation(commandeDtoRequest.localisation());
        commande.setClient(clientRepository.findByNom(commandeDtoRequest.nomClient()).orElseThrow(()->new RuntimeException("le client nexiste pas")));
        commande.setLivreur(livreurRepository.findByNom(commandeDtoRequest.nomLivreur()).orElseThrow(()->new RuntimeException("le livreur n'existe pas")));
        Commande newCommande=commandeRepository.save(commande);
        CommandeDtoResponse commandeDtoResponse=new CommandeDtoResponse(
                newCommande.getId(),
                newCommande.getDateCommande(),
                newCommande.getHeureCommande(),
                newCommande.getDateLivraison(),
                newCommande.getHeureLivraison(),
                newCommande.getMontant(),
                newCommande.getClient().getNom(),
                newCommande.getLivreur().getNom(),
                newCommande.getStatus(),
                newCommande.getLocalisation()
        );
        return new ResponseEntity<>(commandeDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommandeDtoResponse> getCommande(Long id) {
        Optional<Commande> commande=commandeRepository.findById(id);
        if (commande.isPresent()){
            Commande commande1=commande.get();
            CommandeDtoResponse commandeDtoResponse=new CommandeDtoResponse(
                    commande1.getId(),
                    commande1.getDateCommande(),
                    commande1.getHeureCommande(),
                    commande1.getDateLivraison(),
                    commande1.getHeureLivraison(),
                    commande1.getMontant(),
                    commande1.getClient().getNom(),
                    commande1.getLivreur().getNom(),
                    commande1.getStatus(),
                    commande1.getLocalisation()
            );

            return new ResponseEntity<>(commandeDtoResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommande() {
        List<CommandeDtoResponse>commandeDtoResponses=new ArrayList<>();
        List<Commande> commandes=commandeRepository.findAll();
        for (Commande commande:commandes){
            commandeDtoResponses.add(new CommandeDtoResponse(
                    commande.getId(),
                    commande.getDateCommande(),
                    commande.getHeureCommande(),
                    commande.getDateLivraison(),
                    commande.getHeureLivraison(),
                    commande.getMontant(),
                    commande.getClient().getNom(),
                    commande.getLivreur().getNom(),
                    commande.getStatus(),
                    commande.getLocalisation()

            ));
        }
        return new ResponseEntity<>(commandeDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommandeDtoResponse> updateCommande(Long id, CommandeDtoRequest commandeDtoRequest) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        commande.setDateCommande(commandeDtoRequest.dateCommande());
        commande.setHeureCommande(commandeDtoRequest.heureLivraison());
        commande.setDateLivraison(commandeDtoRequest.dateLivraison());
        commande.setHeureLivraison(commandeDtoRequest.heureLivraison());
        commande.setMontant(commandeDtoRequest.montant());
        commande.setStatus(commandeDtoRequest.status());
        commande.setLocalisation(commandeDtoRequest.localisation());
        Commande commandeNew =commandeRepository.save(commande);

        CommandeDtoResponse commandeDtoResponse= new CommandeDtoResponse(
               commandeNew.getId(),
                commandeNew.getDateCommande(),
                commandeNew.getHeureCommande(),
                commandeNew.getDateLivraison(),
                commandeNew.getHeureLivraison(),
                commandeNew.getMontant(),
                commande.getClient().getNom(),
                commande.getLivreur().getNom(),
                commandeNew.getStatus(),
                commandeNew.getLocalisation()
        );
        return new ResponseEntity<>(commandeDtoResponse,HttpStatus.OK);
    }

    @Override
    public String delete(Long id) {
        commandeRepository.deleteById(id);
        return "Commande supprimer";
    }
    @Override
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommandeFromClient(String nom) {
        List<Commande> commandes=commandeRepository.getAllCommandeFromClient(nom);
        List<CommandeDtoResponse> commandeDtoResponses=new ArrayList<>();
        for (Commande commande:commandes){
            commandeDtoResponses.add(new CommandeDtoResponse(
                    commande.getId(),
                    commande.getDateCommande(),
                    commande.getHeureCommande(),
                    commande.getDateLivraison(),
                    commande.getHeureLivraison(),
                    commande.getMontant(),
                    commande.getClient().getNom(),
                    commande.getLivreur().getNom(),
                    commande.getStatus(),
                    commande.getLocalisation()
            ));
        }
        return new ResponseEntity<>(commandeDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDtoResponse>> getAllCommandeFromLivreur(String nom) {
        List<Commande>commandes=commandeRepository.findAll();
        List<CommandeDtoResponse> commandeDtoResponses=new ArrayList<>();
        for (Commande commande:commandes){
            commandeDtoResponses.add(new CommandeDtoResponse(
                    commande.getId(),
                    commande.getDateCommande(),
                    commande.getHeureCommande(),
                    commande.getDateLivraison(),
                    commande.getHeureLivraison(),
                    commande.getMontant(),
                    commande.getClient().getNom(),
                    commande.getLivreur().getNom(),
                    commande.getStatus(),
                    commande.getLocalisation()
            ));
        }
        return new ResponseEntity<>(commandeDtoResponses,HttpStatus.OK);
    }

//    @Override
//    public Client finByNomClient(String nom) {
//        return null;
//    }
}
