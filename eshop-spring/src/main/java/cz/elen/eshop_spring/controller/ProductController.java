package cz.elen.eshop_spring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import cz.elen.eshop_spring.model.Product;
import cz.elen.eshop_spring.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Tímto povolíme frontendu komunikaci s backendem
public class ProductController {

    private final ProductService productService;

    // Controller závisí pouze na Servisní vrstvě, nikoliv na databázi
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}

