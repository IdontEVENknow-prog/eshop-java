package cz.elen.eshop_spring.service;

import cz.elen.eshop_spring.model.Product;
import cz.elen.eshop_spring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Injektujeme nové rozhraní, které dědí od JpaRepository
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        // Voláme vestavěnou metodu Spring Data JPA, která vytáhne data z MySQL
        return productRepository.findAll();
    }
}
