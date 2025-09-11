package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReservation;
    private LocalTime heure;
    private int nbrePersonne;
    @Enumerated(EnumType.STRING)
    StatutReservation status;
    @ManyToOne
    Client client;
    @ManyToOne
    Tables table;
    @OneToMany(mappedBy = "reservation",fetch = FetchType.EAGER)
    private List<Salle>salles=new ArrayList<>();

}
