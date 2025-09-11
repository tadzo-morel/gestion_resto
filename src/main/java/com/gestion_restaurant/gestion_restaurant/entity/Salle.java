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
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long capacite;
    private StatusEspace status;
    @OneToMany(mappedBy = "salle")
    private List<Tables> tables=new ArrayList<>();
    @ManyToOne
    Reservation reservation;
}
