package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.Categories;
import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.model.ViewModels;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import fr.mercadona.mercadona.repository.ProductsRepository;
import fr.mercadona.mercadona.service.ProductsNotFoundException;
import fr.mercadona.mercadona.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

    //NEW
    @Value("${upload.directory}")
    private String uploadDirectory;





    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsService productsService;


    @GetMapping("/produits/intranet")
    public String showProductsList(Model model) {
        List<Products> listProducts = productsService.listAll();

// Pour chaque produit, ajoutez le chemin d'accès à l'image
        listProducts.forEach(product -> {
            String imageFileName = product.getImageName(); // Obtenir le nom du fichier image depuis la base de données
            String imageUrl = "/static/images/" + imageFileName; // Chemin d'accès à l'image sur le système de fichiers
            product.setImageName(imageUrl); // S'Assurer que setImageName() est le bon setter pour l'URL de l'image
        });

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


    @GetMapping("/produits/nouveau")
    public String showNewFormProduits(Model model) {
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("products", new Products());
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("pageTitle", "Veuillez intégrer votre nouveau produit");
        return "product_form";
    }




    @PostMapping("/produits/save")
    public String saveProducts(Products products, RedirectAttributes response) {
        productsService.save(products);
        response.addFlashAttribute("message", "Votre produit a bien été enregistré");

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
            response.addFlashAttribute("message", "Votre produit ayant l'ID : " + id + " a bien été modifié");
            return "redirect:/produits/intranet";
        }
    }


    @GetMapping("/produits/delete/{id}")
    public String deleteProducts(@PathVariable("id") Integer id, RedirectAttributes response) {
        try {
            productsService.delete(id);
            response.addFlashAttribute("message", "Le produit n° " + id + "a bien été supprimé");
        } catch (ProductsNotFoundException e) {
            response.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/produits/intranet";

    }


    @PostMapping("/produits/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("imageFile") MultipartFile file) {
        try {
            // Vérifiez si le fichier n'est pas vide
            if (!file.isEmpty()) {
                // Générez un nom de fichier unique pour éviter les conflits
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
                String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

                // Enregistrez l'image sur le disque
                String imagePath =  "src/main/resources/static/images/" + uniqueFileName;
                try (FileOutputStream outputStream = new FileOutputStream(imagePath)) {
                    outputStream.write(file.getBytes());
                }

                // Enregistrez le nom unique du fichier dans la colonne 'imageName' de votre base de données
                Products product = new Products();
                product.setImageName(uniqueFileName); // Stockez le nom unique du fichier
                product.setImage(file.getBytes()); // Stockez les données binaires de l'image
                productsService.save(product);

                return ResponseEntity.ok("L'image " + file.getOriginalFilename() + " a été téléchargée et stockée avec succès.");
            } else {
                return ResponseEntity.badRequest().body("Le fichier est vide.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors du téléchargement et du stockage de l'image.");
        }
    }









}











