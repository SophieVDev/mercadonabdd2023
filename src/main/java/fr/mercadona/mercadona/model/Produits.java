package fr.mercadona.mercadona.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Produits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String photoUrl;
    private LocalDate expirationDate;


    public Produits(String nom, String description, double prix, String photoUrl, LocalDate expirationDate) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.photoUrl = photoUrl;
        this.expirationDate = expirationDate;

    }

    public Produits() {
    }
}
