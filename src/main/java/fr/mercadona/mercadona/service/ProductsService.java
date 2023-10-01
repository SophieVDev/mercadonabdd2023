package fr.mercadona.mercadona.service;


import fr.mercadona.mercadona.model.Categories;
import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import fr.mercadona.mercadona.repository.ProductsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class ProductsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductsRepository productsRepository;



    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Products> listAll(){
        return (List<Products>) productsRepository.findAll();
    }

    public void save(Products products){
        productsRepository.save(products);
    }

    public Products get(Integer id) throws ProductsNotFoundException{
        Optional<Products> result = productsRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductsNotFoundException("Nous ne trouvons pas de produit avec cet ID : " + id);
    }

    public void delete(Integer id)  throws ProductsNotFoundException{
        Long count = productsRepository.countById(id);
        if (count == null || count == 0) {
            throw new ProductsNotFoundException("Nous ne trouvons aucun utilisateur ayant cette ID : " + id);

        }
        productsRepository.deleteById(id);
    }


    public Products createProduct(Products products) {
        if (products.getNom() == null || products.getNom().isEmpty() || products.getPrix() <= 0) {
            throw new IllegalArgumentException("Les données du produit sont invalides");
        }

        // Création du produit
        Products newProducts = new Products();
        newProducts.setNom(products.getNom());
        newProducts.setPrix(products.getPrix());

        // Enregistrement dans la base de données
        return productsRepository.save(newProducts);
    }

    public Products getProductsById(Integer productsId) {
        return productsRepository.findById(productsId).orElse(null);
    }

    public Products updateProducts(Integer productsId, Products updatedProducts) {
        Products existingProducts = getProductsById(productsId);
        if (existingProducts == null) {
            return null;
        }

        // Mise à jour des propriétés du produit existant
        existingProducts.setNom(updatedProducts.getNom());
        existingProducts.setPrix(updatedProducts.getPrix());

        return productsRepository.save(existingProducts);
    }

    public void deleteProduct(Integer productId) {
        productsRepository.deleteById(productId);
    }


}








