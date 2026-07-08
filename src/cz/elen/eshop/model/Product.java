package cz.elen.eshop.model;


public class Product {
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    private Long id;
    private String name;
    private double price;
    private int stockQuantity;
    private Category category; //vztah mezi objekty

    public Product(Long id,
                   String name,
                   double price,
                   int stockQuantity,
                   Category category) {             //konstruktor

        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
}