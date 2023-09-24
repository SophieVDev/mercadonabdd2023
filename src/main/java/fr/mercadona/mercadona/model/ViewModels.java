package fr.mercadona.mercadona.model;

import java.util.List;

public class ViewModels {
    private List<Products> listProducts;


    public ViewModels() {
        // Constructeur par défaut
    }

    public ViewModels(List<Products> listProducts) {
        this.listProducts = listProducts;

    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }



}