package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nomPlat;
    private String description;
    private double prix;
    @ManyToOne
    Commande commande;
    @OneToMany(mappedBy = "articleMenu",fetch = FetchType.EAGER)
    private List<LigneDeCommande> commandes=new ArrayList<>();
}
