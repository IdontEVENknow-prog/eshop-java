package cz.elen.eshop_spring.repository;

import java.util.Optional;
import cz.elen.eshop_spring.model.Product;

import java.util.List;


public interface ProductRepositoryInterface {

    void save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByName(String name);

    List<String> getAllProductNames();
}