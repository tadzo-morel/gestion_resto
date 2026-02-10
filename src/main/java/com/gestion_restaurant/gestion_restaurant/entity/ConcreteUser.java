package com.gestion_restaurant.gestion_restaurant.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// @AllArgsConstructor
// @NoArgsConstructor
@Getter
@Setter
public class ConcreteUser extends User {
    // Classe concrète pour permettre l'instanciation de User
}