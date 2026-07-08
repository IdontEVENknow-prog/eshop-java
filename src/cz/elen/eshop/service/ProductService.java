package cz.elen.eshop.service;

import cz.elen.eshop.exception.ProductNotFoundException;
import cz.elen.eshop.model.Product;
import cz.elen.eshop.repository.ProductRepositoryInterface;
import java.util.Optional;
import java.util.List;

public class ProductService {

    private final ProductRepositoryInterface productRepository;

    public ProductService(ProductRepositoryInterface productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Produkt s ID " + id + " nebyl nalezen."
                        ));
    }

    public List<Product> searchProductsByName(String name) {

        return productRepository.findByName(name);

    }

    public List<String> getAllProductNames() {

        return productRepository.getAllProductNames();

    }
}