package fr.mercadona.mercadona.service;

import fr.mercadona.mercadona.model.Product;
import fr.mercadona.mercadona.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> listAll(){
        return (List<Product>) productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

public Product get(Integer id) throws ProductsNotFoundException{
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductsNotFoundException("Nous ne trouvons pas de produit avec cet ID : " + id);
}

    public void delete(Integer id)  throws ProductsNotFoundException{
        Long count = productRepository.countById(id);
        if (count == null || count == 0) {
            throw new ProductsNotFoundException("Nous ne trouvons aucun utilisateur ayant cette ID : " + id);

        }
        productRepository.deleteById(id);
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

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Integer productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);
        if (existingProduct == null) {
            return null;
        }

        // Mise à jour des propriétés du produit existant
        existingProduct.setNom(updatedProduct.getNom());
        existingProduct.setPrix(updatedProduct.getPrix());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
