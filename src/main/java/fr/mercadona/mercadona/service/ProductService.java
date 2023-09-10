package fr.mercadona.mercadona.service;

import fr.mercadona.mercadona.model.Product;
import fr.mercadona.mercadona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product createProduct(Product product) {
        if (product.getNom() == null || product.getNom().isEmpty() || product.getPrix() <= 0) {
            throw new IllegalArgumentException("Les données du produit sont invalides");
        }

        // Création du produit
        Product newProduct = new Product();
        newProduct.setNom(product.getNom());
        newProduct.setPrix(product.getPrix());

        // Enregistrement dans la base de données
        return productRepository.save(newProduct);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);
        if (existingProduct == null) {
            return null;
        }

        // Mise à jour des propriétés du produit existant
        existingProduct.setNom(updatedProduct.getNom());
        existingProduct.setPrix(updatedProduct.getPrix());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
