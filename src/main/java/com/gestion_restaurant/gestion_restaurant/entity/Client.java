package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("Client")
public class Client extends User {
    private String localisation;
    private String password;
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
    @OneToMany(mappedBy = "client")
    private List<Reservation>reservations;

}
