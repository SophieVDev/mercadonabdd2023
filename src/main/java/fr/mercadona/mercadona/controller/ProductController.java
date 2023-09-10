package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.Product;
import fr.mercadona.mercadona.model.ProduitPromo;
import fr.mercadona.mercadona.service.ProductService;
import fr.mercadona.mercadona.service.ProduitPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);

        return "products";
    }
}
