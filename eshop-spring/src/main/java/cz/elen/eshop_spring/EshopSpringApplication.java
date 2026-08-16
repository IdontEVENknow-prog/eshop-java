package cz.elen.eshop_spring;

import cz.elen.eshop_spring.model.Product;
import cz.elen.eshop_spring.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EshopSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopSpringApplication.class, args);
	}

	// Tento blok se spustí ihned po startu a naplní prázdnou MySQL daty
	@Bean
	public CommandLineRunner databaseSeeder(ProductRepository productRepository) {
		return args -> {
			// Pokud v MySQL nic není, vložíme testovací data
			if (productRepository.count() == 0) {
				System.out.println("Analytický log: Databáze je prázdná. Vkládám výchozí produkty...");

				productRepository.save(new Product(null, "Notebook", 25000.0, "Výkonný pracovní notebook"));
				productRepository.save(new Product(null, "Mobilní telefon", 15000.0, "Chytrý telefon s foťákem"));
				productRepository.save(new Product(null, "Bezdrátová sluchátka", 3000.0, "Sluchátka s ANC"));

				System.out.println("Analytický log: Výchozí produkty byly úspěšně uloženy do MySQL.");
			} else {
				System.out.println("Analytický log: Databáze již obsahuje produkty, přeskakuji vkládání.");
			}
		};
	}
}
