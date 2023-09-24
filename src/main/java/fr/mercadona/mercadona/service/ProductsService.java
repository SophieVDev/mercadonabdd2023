package fr.mercadona.mercadona.service;

import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.repository.ProductsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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



    /////////IMAGES//////////////

    public byte[] loadProductsImage(String imagePath) {
        try {
            // Créez un objet File en utilisant le chemin de l'image
            File imageFile = new File(imagePath);

            // Vérifiez si le fichier existe
            if (imageFile.exists()) {
                // Créez un tableau d'octets pour stocker le contenu de l'image
                byte[] imageContent = new byte[(int) imageFile.length()];

                // Utilisez FileInputStream pour lire le contenu de l'image dans le tableau d'octets
                FileInputStream fileInputStream = new FileInputStream(imageFile);
                fileInputStream.read(imageContent);
                fileInputStream.close();

                // Retournez le contenu de l'image sous forme de tableau d'octets
                return imageContent;
            } else {
                // Gérez le cas où le fichier image n'existe pas
                // Vous pouvez retourner null ou lancer une exception appropriée selon votre choix
                return null;
            }
        } catch (IOException e) {
            // Gérez les exceptions en conséquence
            e.printStackTrace();
            return null; // ou lancez une exception appropriée si nécessaire
        }
    }


    // ... Autres méthodes de service ...

    public String getProductsImageById(Integer id) {
        // Utilisez l'EntityManager pour rechercher le produit par son ID
        Products product = entityManager.find(Products.class, id);

        if (product != null) {
            // Récupérez le chemin de l'image depuis le produit
            String imagePath = product.getPhotoUrl();
            return imagePath;
        } else {
            // Gérez le cas où aucun produit avec l'ID spécifié n'a été trouvé
            return null; // Ou vous pouvez jeter une exception ou renvoyer une valeur par défaut, selon votre logique
        }
    }

    //////CALCUL REMISE//////////////

    /*public Products findById(Integer id) {
        // Implémentez la logique pour récupérer un produit par son ID
        return productsRepository.findById(id).orElse(null); // Supposons que le repository gère la recherche par ID
    }

    public void save(Products products) {
        // Implémentez la logique pour sauvegarder un produit en base de données
        productsRepository.save(products); // Supposons que le repository gère la sauvegarde
    }
*/



}


