package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.Categories;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import fr.mercadona.mercadona.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")


public class CategoriesController {

    @Autowired
    private CategoriesRepository repo;

    @GetMapping("/categories")
    public String listCategories(Model model){
        List<Categories> listCategories = repo.findAll();
        model.addAttribute("listCategories",listCategories);
        return"product_form";
    }

    @GetMapping("/categories/nouveau")
    public String showCategoriesNewForm(Model model){
        model.addAttribute("Categories",new Categories());
        return"product_form";
    }

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/list")
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }


}
