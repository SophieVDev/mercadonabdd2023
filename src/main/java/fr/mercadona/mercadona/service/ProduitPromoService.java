package fr.mercadona.mercadona.service;

import fr.mercadona.mercadona.model.ProduitPromo;
import fr.mercadona.mercadona.repository.ProduitPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitPromoService {
    @Autowired
    private ProduitPromoRepository produitPromoRepository;

    public List<ProduitPromo> getProductsByCategorie(String categorie) {
        return produitPromoRepository.findByCategorie(categorie);
    }

    public List<ProduitPromo> getAllProduitPromo() {
        return produitPromoRepository.findAll();
    }


    public ProduitPromo createProduitPromo(ProduitPromo produitPromo) {
        if (produitPromo.getNom() == null || produitPromo.getNom().isEmpty() || produitPromo.getPrix() <= 0) {
            throw new IllegalArgumentException("Les données du produit sont invalides");
        }

        // Création du produit
        ProduitPromo newProduitPromo = new ProduitPromo();
        newProduitPromo.setNom(produitPromo.getNom());
        newProduitPromo.setPrix(produitPromo.getPrix());

        // Enregistrement dans la base de données
        return produitPromoRepository.save(newProduitPromo);
    }

    public ProduitPromo getProduitPromoById(Long produit_PromoId) {
        return produitPromoRepository.findById(produit_PromoId).orElse(null);
    }

    public ProduitPromo updateProduitPromo(Long produit_PromoId, ProduitPromo updatedProduitPromo) {
        ProduitPromo existingProduitPromo = getProduitPromoById(produit_PromoId);
        if (existingProduitPromo == null) {
            return null;
        }

        // Mise à jour des propriétés du produit existant
        existingProduitPromo.setNom(updatedProduitPromo.getNom());
        existingProduitPromo.setPrix(updatedProduitPromo.getPrix());

        return produitPromoRepository.save(existingProduitPromo);
    }

    public void deleteProduct(Long productId) {
        produitPromoRepository.deleteById(productId);
    }

}


