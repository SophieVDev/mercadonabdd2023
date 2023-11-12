package fr.mercadona.mercadona;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MercadonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadonaApplication.class, args);
	}}
