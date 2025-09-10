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
@DiscriminatorValue("Client")
public class Client extends User {
    private String localisation;
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Commande> commandes=new ArrayList<>();
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Reservation>reservations=new ArrayList<>();

}
