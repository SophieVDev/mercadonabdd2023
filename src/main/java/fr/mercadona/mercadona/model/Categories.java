package fr.mercadona.mercadona.model;

import jakarta.persistence.*;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=45,nullable = false, unique = true)
    private String name;

    public Categories() {
    }

    public Categories(Integer id) {
        this.id = id;
    }

    public Categories(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
