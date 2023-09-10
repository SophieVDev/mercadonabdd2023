package fr.mercadona.mercadona.controller;
import fr.mercadona.mercadona.model.ProduitPromo;
import fr.mercadona.mercadona.service.ProduitPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProduitPromoController {
    @Autowired
    private ProduitPromoService produitPromoService;

    @GetMapping("/promotions")
    public String showProducts(Model model) {
        List<ProduitPromo> produitPromo= produitPromoService.getAllProduitPromo();
        model.addAttribute("produitPromo", produitPromo);
        return "promotions";
    }

}
