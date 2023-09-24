package fr.mercadona.mercadona;

import fr.mercadona.mercadona.model.Products;
import fr.mercadona.mercadona.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductsRepository repo;


    @Test
    public void TestAddNew(){
        Products products = new Products();
        products.setNom("Table");
        products.setDescription("test description");
        products.setPrix(12);
        products.setExpirationDate("01/06/2024");

        Products savedProducts = repo.save(products);
    }

    @Test
    public void testListAll(){
        Iterable<Products> listProducts = repo.findAll();

        for (Products products : listProducts){
            System.out.println(listProducts);
        }
    }

    @Test
    public void TestUpdate(){
        Integer ProductsId = 1;
        Optional<Products> optionalProducts = repo.findById(ProductsId);
        Products products = optionalProducts.get();
        products.setNom("Table");
        repo.save(products);
        Products updatedProducts = repo.findById(ProductsId).get();
        assertThat(updatedProducts.getNom()).isEqualTo("Table");

    }

    @Test
    public void testGet(){
     Integer productsId = 2 ;
        Optional<Products> optionalProducts = repo.findById(productsId);
        assertThat(optionalProducts).isPresent();
        System.out.println(optionalProducts.get());
    }

    @Test
    public void TestDelete(){
        Integer productsId = 2;
    repo.deleteById(productsId);

        Optional<Products> optionalProducts = repo.findById(productsId);
        assertThat(optionalProducts).isNotPresent();
    }
}
