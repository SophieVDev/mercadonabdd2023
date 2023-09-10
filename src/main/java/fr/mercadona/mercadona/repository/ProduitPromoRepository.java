package fr.mercadona.mercadona.repository;


import fr.mercadona.mercadona.model.ProduitPromo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProduitPromoRepository extends JpaRepository<ProduitPromo, Long> {
    List<ProduitPromo> findByCategorie(String categorie);
}
