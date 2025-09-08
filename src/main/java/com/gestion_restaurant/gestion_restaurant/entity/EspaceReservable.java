package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.aot.generate.Generated;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "espace_reservable")
public abstract class EspaceReservable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    StatusEspaceReservable status;
    @OneToMany(mappedBy = "espaceReservable")
    private List<Reservation>reservations=new ArrayList<>();
}
