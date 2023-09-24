package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.ViewModels;
import fr.mercadona.mercadona.model.Product;
import fr.mercadona.mercadona.service.ProductService;
import fr.mercadona.mercadona.service.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class ProductController {
   /* @Autowired
    private ProductService productService;


    @GetMapping("/products/intranet")
    public String showProductList(Model model) {
        List<Product> listProduct = productService.listAll();


        ViewModels viewModels = new ViewModels();
        viewModels.setListProduct(listProduct);

        model.addAttribute("viewModels", viewModels);


        return "personal-page";
    }

    @GetMapping("/products")
    public String showProduct(Model model) {
        List<Product> product = productService.getAllProducts();
        model.addAttribute("product", product);

        return "products";
    }


@GetMapping ("/products/nouveau")
public String showNewForm(Model model){
        model.addAttribute("product", new Product());
    model.addAttribute("pageTitle", "Veuillez intégrer votre nouveau produit");
        return"product_form";
}

@PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes response) {
    productService.save(product);
    response.addFlashAttribute("message","Votre produit a bien été enregistré");

    return "redirect:/products/intranet";
}

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model, RedirectAttributes response) {
        try {
            Product product = productService.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Modification du produit ayant l'ID : " + id);
            return "product_form";
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", "Votre produit ayant l'ID : " + id +" a bien été modifié");
            return "redirect:/products/intranet";
        }
    }


    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id")Integer id, RedirectAttributes response) {
        try {
            productService.delete(id);
            response.addFlashAttribute("message", "Le produit n° "+id+"a bien été supprimé");
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/products/intranet";

    }
*/
}
