package com.gestion_restaurant.gestion_restaurant.DTO;

import com.gestion_restaurant.gestion_restaurant.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequestDTO {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String localisation;
    private String password;

}
