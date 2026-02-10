package com.gestion_restaurant.gestion_restaurant.entity;

 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;

// @Entity
// @AllArgsConstructor
// @NoArgsConstructor
// @Getter
// @Setter
// @Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "user")
// public abstract class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String nom;
//     private  String  prenom;
//     private String telephone;
//     private  String email;
//     private String password;

// }
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String prenom;
    private String telephone;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;

    // Méthodes manquantes
    // public String getNom() {
    //     return nom;
    // }

    // public String getPrenom() {
    //     return prenom;
    // }

    // public String getTelephone() {
    //     return telephone;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public Long getId() {
    //     return id;
    // }

    // public void setNom(String nom) {
    //     this.nom = nom;
    // }

    // public void setPrenom(String prenom) {
    //     this.prenom = prenom;
    // }

    // public void setTelephone(String telephone) {
    //     this.telephone = telephone;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }
}
