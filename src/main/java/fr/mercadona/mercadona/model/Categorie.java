package fr.mercadona.mercadona.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @ManyToMany(mappedBy = "categories")
    private Set<ProduitPromo> produitsPromo = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie() {
    }
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
