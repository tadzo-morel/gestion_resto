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
@Setter
@Getter
@Table(name = "`table`") // Important: "table" est un mot réservé SQL
public class Tables {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer nbrePlace; // Correction: camelCase
    
    @Enumerated(EnumType.STRING)
    private StatusEspace status = StatusEspace.disponible; // Valeur par défaut
    
    // Relation ManyToOne avec Salle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_id")
    private Salle salle;
    
    // Relation OneToMany avec Reservation
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();
}