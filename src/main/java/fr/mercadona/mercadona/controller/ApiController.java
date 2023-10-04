package fr.mercadona.mercadona.controller;
import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import fr.mercadona.mercadona.service.CategoriesService;
import fr.mercadona.mercadona.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")

public class ApiController {

    @Autowired
    private ProductsService productsService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("/produits")
    public List<Products> getAllProducts() {
        List<Products> products = productsService.getAllProducts();

        for (Products product : products) {
            product.setPhotoUrl("/products/image/" + product.getId());
        }

        return products;
    }



    @PostMapping("/produits")
    public ResponseEntity<Products> createProduct(@RequestBody Products products) {
        Products createdProducts = productsService.createProduct(products);
        return ResponseEntity.ok(createdProducts);

    }

    @GetMapping("/produits/{productId}")
    public ResponseEntity<Products> getProductsById(@PathVariable Integer productId) {
        Products products = productsService.getProductsById(productId);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/produits/{productId}")
    public ResponseEntity<Products> updateProduct(@PathVariable Integer productsId, @RequestBody Products products) {
        Products updatedProducts = productsService.updateProducts(productsId, products);
        if (updatedProducts != null) {
            return ResponseEntity.ok(updatedProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/produits/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productsId) {
        productsService.deleteProduct(productsId);
        return ResponseEntity.noContent().build();
    }


}





