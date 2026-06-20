package cz.elen.eshop;

import cz.elen.eshop.model.Category;
import cz.elen.eshop.model.Product;
import cz.elen.eshop.repository.ProductRepository;
import cz.elen.eshop.service.ProductService;

public class Main {

    public static void main(String[] args) {

        Category electronics =
                new Category(1L, "Elektronika");

        Product notebook =
                new Product(
                        1L,
                        "ASUS ROG",
                        35000,
                        10,
                        electronics
                );

        Product mouse =
                new Product(
                        2L,
                        "Logitech G502",
                        1200,
                        25,
                        electronics
                );

        ProductRepository repository =
                new ProductRepository();

        ProductService service =
                new ProductService(repository);

        service.addProduct(notebook);
        service.addProduct(mouse);

        System.out.println(service.getAllProducts());
    }
}