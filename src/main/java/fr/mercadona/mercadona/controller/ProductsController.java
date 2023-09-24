package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.Categories;
import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.model.ViewModels;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import fr.mercadona.mercadona.repository.ProductsRepository;
import fr.mercadona.mercadona.service.ProductsNotFoundException;
import fr.mercadona.mercadona.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductsController {
@Autowired
private ProductsRepository productsRepository;

@Autowired
private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsService productsService;



    @GetMapping("/produits/intranet")
    public String showProductsList(Model model) {
        List<Products> listProducts = productsService.listAll();


        ViewModels viewModels = new ViewModels();
        viewModels.setListProducts(listProducts);

        model.addAttribute("viewModels", viewModels);


        return "personal-page";
    }

    @GetMapping("/produits")
    public String showProducts(Model model) {
        List<Products> products = productsService.getAllProducts();
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categoriesList", categoriesList);

        return "products";
    }


    @GetMapping ("/produits/nouveau")
    public String showNewFormProduits(Model model){
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("products", new Products());
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("pageTitle", "Veuillez intégrer votre nouveau produit");
        return"product_form";
    }

    @PostMapping("/produits/save")
    public String saveProducts(Products products, RedirectAttributes response) {
        productsService.save(products);
        response.addFlashAttribute("message","Votre produit a bien été enregistré");

        return "redirect:/produits/intranet";
    }

    @GetMapping("/produits/edit/{id}")
    public String showEditProductsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes response) {
        try {
            Products products = productsService.get(id);
            List<Categories> categoriesList = categoriesRepository.findAll();
            model.addAttribute("products", products);
            model.addAttribute("categoriesList", categoriesList);
            model.addAttribute("pageTitle", "Modification du produit ayant l'ID : " + id);
            return "product_form";
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", "Votre produit ayant l'ID : " + id +" a bien été modifié");
            return "redirect:/produits/intranet";
        }
    }


    @GetMapping("/produits/delete/{id}")
    public String deleteProducts(@PathVariable("id")Integer id, RedirectAttributes response) {
        try {
            productsService.delete(id);
            response.addFlashAttribute("message", "Le produit n° "+id+"a bien été supprimé");
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/produits/intranet";

    }

///IMAGES VISBLES/////
@PostMapping("/produits/upload")
public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute Products products) {
    if (!file.isEmpty()) {
        try {
            // Spécifiez le chemin complet vers le bureau
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

            // Assurez-vous que le dossier "imagemercadona" existe sur le bureau
            File imageMercadonaFolder = new File(desktopPath + File.separator + "imagemercadona");
            if (!imageMercadonaFolder.exists()) {
                imageMercadonaFolder.mkdir();
            }

            // Enregistrez le fichier sur le bureau
            File desktopFile = new File(desktopPath + File.separator + "imagemercadona" + File.separator + file.getOriginalFilename());
            file.transferTo(desktopFile);

            // Mettez à jour le chemin de l'image dans l'objet Product
            String imagePath = "imagemercadona/" + file.getOriginalFilename();
            products.setImagePath(imagePath);

            // Enregistrez le produit mis à jour dans la base de données
            productsService.save(products);

            return "redirect:/success"; // Redirigez vers une page de succès
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error"; // Redirigez vers une page d'erreur en cas de problème
        }
    } else {
        return "redirect:/error"; // Redirigez vers une page d'erreur si aucun fichier n'a été téléchargé
    }
}



    @GetMapping("/produits/image/{id}")
    public ResponseEntity<byte[]> getProductsImage(@PathVariable ("id")Integer id) {
        // Récupérez le chemin de l'image depuis la base de données en utilisant l'ID du produit
        String imagePath = productsService.getProductsImageById(id);

        // Chargez le contenu de l'image depuis le dossier "imagemercadona"
        byte[] imageContent = productsService.loadProductsImage(imagePath);

        // Construisez une réponse avec le contenu de l'image et les en-têtes appropriés
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageContent.length);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }
}

    // ...







//////////////REMISE//////////////////////////////

  /*  @PostMapping("/appliquer-remise")
    public String appliquerRemise(@RequestParam("id") Integer id, @RequestParam("promotion") Double promotion, RedirectAttributes response) {

        try {
            Products product = new Products();
            // Récupérez le produit par son ID
            Products products = productsService.get(id);

            if (products != null) {
                // Appliquez la remise au produit en fonction de la promotion
                double prixProduit = product.getPrix();
                double prixRemise = prixProduit * (1 - promotion / 100); // Appliquez la remise
                product.setPrix(prixRemise);

                // Enregistrez le produit mis à jour
                productsService.save(products);

                response.addFlashAttribute("message", "Remise appliquée avec succès au produit n°" + id);
            } else {
                response.addFlashAttribute("message", "Produit introuvable avec l'ID : " + id);
            }
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", e.getMessage());
        }

        return "product_form"; // Redirigez vers la page intranet ou toute autre page souhaitée
    }
    */

    /*@GetMapping("/byCategory")
    public List<Products> getProductsByCategory(@RequestParam Long categoryId) {
        return productsService.getProductsByCategory(categoryId);
    }*/


