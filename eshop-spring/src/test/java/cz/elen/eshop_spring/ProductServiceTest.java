package cz.elen.eshop_spring;

import cz.elen.eshop_spring.model.Product;
import cz.elen.eshop_spring.repository.ProductRepository;
import cz.elen.eshop_spring.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // Vytvoříme "falešný" (namockovaný) repozitář, abychom netestovali skutečnou databázi
        productRepository = Mockito.mock(ProductRepository.class);
        // Předáme tento mock do naší testované služby (Dependency Injection v praxi)
        productService = new ProductService(productRepository);
    }

    @Test
    void testGetAllProducts_ShouldReturnProductList() {
        // 1. GIVEN (Příprava dat a definice chování mocku)
        Product mockProduct = new Product(1L, "Testovací Notebook", 10000.0, "Popis");
        List<Product> expectedProducts = List.of(mockProduct);

        // Říkáme: Až služba zavolá .findAll(), vrať toto připravené pole
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // 2. WHEN (Spuštění testované metody)
        List<Product> actualProducts = productService.getAllProducts();

        // 3. THEN (Ověření výsledků pomocí Assertů)
        assertEquals(1, actualProducts.size(), "Seznam by měl obsahovat přesně 1 produkt.");
        assertEquals("Testovací Notebook", actualProducts.get(0).getName(), "Název produktu se musí shodovat.");
        assertEquals(10000.0, actualProducts.get(0).getPrice(), "Cena produktu se musí shodovat.");
    }
}
