package fr.mercadona.mercadona.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false,unique = true)
    private String nom;
    private String description;
    private double prix;
    private double promotion;
    private String photoUrl;
    private String expirationDate;
    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name="categories_id")
    private Categories categories;
    public Products() {
    }


    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", promotion=" + promotion +
                ", photoUrl='" + photoUrl + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }

    public Products(String nom, String description, double prix, double promotion, String photoUrl, String expirationDate) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.promotion = promotion;
        this.photoUrl = photoUrl;
        this.expirationDate = expirationDate;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
