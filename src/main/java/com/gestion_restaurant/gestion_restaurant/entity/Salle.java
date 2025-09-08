package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("salle")
public class Salle extends EspaceReservable{
    private Long capacite;
    @OneToMany(mappedBy = "salle")
    private List<Tables> tables=new ArrayList<>();
}
