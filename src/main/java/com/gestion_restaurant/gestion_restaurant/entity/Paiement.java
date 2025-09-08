package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date_paiement;
    private LocalTime heure_paiement;
    private double montant;
    @Enumerated(EnumType.STRING)
    ModePaiement mode;
    @OneToOne
    Commande commands;
    @OneToOne(mappedBy = "paiement")
    Facture facture;



}
