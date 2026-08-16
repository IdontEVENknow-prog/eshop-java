package cz.elen.eshop_spring.repository;

import cz.elen.eshop_spring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Třívrstvá architektura zůstává zachována.
    // JpaRepository nám automaticky dodá všechny SQL operace (SELECT, INSERT, UPDATE, DELETE).
}
