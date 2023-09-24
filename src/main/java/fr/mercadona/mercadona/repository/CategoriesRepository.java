package fr.mercadona.mercadona.repository;

import fr.mercadona.mercadona.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer>{
}
