package fr.mercadona.mercadona.service;

public class ProductsNotFoundException extends Throwable {
    public ProductsNotFoundException(String message) {
        super(message);
    }
}