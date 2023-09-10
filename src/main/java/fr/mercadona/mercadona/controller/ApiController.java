package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.Product;
import fr.mercadona.mercadona.model.ProduitPromo;
import fr.mercadona.mercadona.service.ProductService;
import fr.mercadona.mercadona.service.ProduitPromoService;
import fr.mercadona.mercadona.service.ProduitPromoService;
import fr.mercadona.mercadona.repository.ProduitPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProduitPromoService produitPromoService;

    @Autowired
    private ProduitPromoRepository produitPromoRepository;
    @GetMapping("/products")
    public List<Product> getAllProducts() {
            return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/promo")
    public List<ProduitPromo> getAllProduitPromo() {
        return produitPromoService.getAllProduitPromo();
    }

    @PostMapping("/promo")
    public ResponseEntity<ProduitPromo> createProduitPromo(@RequestBody ProduitPromo produitPromo) {
        ProduitPromo createdProduitPromo = produitPromoService.createProduitPromo(produitPromo);
        return ResponseEntity.ok(createdProduitPromo);
    }
    @GetMapping("/promo/{produit_promoId}")
    public ResponseEntity<ProduitPromo> getProduitPromoById(@PathVariable Long produit_promoId) {
        ProduitPromo produitPromo = produitPromoService.getProduitPromoById(produit_promoId);
        if (produitPromo != null) {
            return ResponseEntity.ok(produitPromo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/promo/{produit_promoCategorie}")
    public List<ProduitPromo> getProductsByCategory(@PathVariable ("categorie") String categorie) {
        // Utilisez votre service de produit pour récupérer les produits par catégorie
        List<ProduitPromo> products = produitPromoService.getProductsByCategorie(categorie);
        return products;
    }

    @PutMapping("/promo/{produit_promoId}")
    public ResponseEntity<ProduitPromo> updateProduitPromo(@PathVariable Long produit_promoId, @RequestBody ProduitPromo produitPromo) {
        ProduitPromo updatedProduitPromo = produitPromoService.updateProduitPromo(produit_promoId, produitPromo);
        if (updatedProduitPromo != null) {
            return ResponseEntity.ok(updatedProduitPromo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/promo/{produit_promoId}")
    public ResponseEntity<Void> deleteProduitPromo(@PathVariable Long produit_promoId) {
        produitPromoService.deleteProduct(produit_promoId);
        return ResponseEntity.noContent().build();
    }

}


