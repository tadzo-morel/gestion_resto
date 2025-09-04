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
@DiscriminatorValue("livreur")
public class Livreur extends User{
    @Enumerated(EnumType.STRING)
    private StatutLivreur status;
    private String password;
    @OneToMany(mappedBy = "livreur")
    private List<Commande>commandes;
}
