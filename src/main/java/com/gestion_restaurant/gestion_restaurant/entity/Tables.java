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
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numTable;
    private  int capaciteTable;
    @Enumerated(EnumType.STRING)
    StatutTable status;
    @OneToMany(mappedBy = "table", fetch = FetchType.EAGER)
    private List<Reservation>reservations=new ArrayList<>();
}
