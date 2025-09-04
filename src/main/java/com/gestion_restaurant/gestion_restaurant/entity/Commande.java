package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCommande;
    private LocalTime heure;
    private Long montant;
    @Enumerated(EnumType.STRING)
    StatutCommande status;
    private  String localisation;
    @ManyToOne
    Client client;
    @ManyToOne
    Livreur livreur;
    @OneToMany(mappedBy = "commande",fetch = FetchType.EAGER)
    private List<ArticleMenu> articleMenus=new ArrayList<>();

}
