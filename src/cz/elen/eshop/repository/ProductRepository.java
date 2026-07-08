package cz.elen.eshop.repository;

import cz.elen.eshop.model.Product;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductRepositoryInterface {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {

        for (Product product : products) {

            if (product.getId().equals(id)) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Product> findByName(String name) {

        return products.stream()
                .filter(product ->
                        product.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<String> getAllProductNames() {

        return products.stream()
                .map(Product::getName)
                .toList();

    }
}