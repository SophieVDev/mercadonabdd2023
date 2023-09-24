package fr.mercadona.mercadona;

import fr.mercadona.mercadona.model.Categories;
import fr.mercadona.mercadona.repository.CategoriesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoriesRepositoryTest {
    @Autowired
    private CategoriesRepository repo;

    @Test
    public void testCreateCategories(){
        Categories savedCategories = repo.save(new Categories("Maison"));
        assertThat(savedCategories.getId()).isGreaterThan(0);
    }
}
