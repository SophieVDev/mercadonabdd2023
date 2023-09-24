package fr.mercadona.mercadona.repository;

import fr.mercadona.mercadona.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, CrudRepository<Product, Integer> {
    public Long countById(Integer id);
}
