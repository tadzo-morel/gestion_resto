package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "salles")
public class Salle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer capacite;
    
    @Enumerated(EnumType.STRING)
    private StatusEspace status = StatusEspace.disponible;
    
    // Relation OneToMany avec Tables
    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tables> tables = new ArrayList<>();
    
    // Relation OneToMany avec Reservation (mappée par la propriété salle dans Reservation)
    @OneToMany(mappedBy = "salle", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reservation> reservations = new ArrayList<>();
    
    // Méthodes utilitaires pour gérer les tables
    public void addTable(Tables table) {
        tables.add(table);
        table.setSalle(this);
    }
    
    public void removeTable(Tables table) {
        tables.remove(table);
        table.setSalle(null);
    }
}