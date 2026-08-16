package cz.elen.eshop_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products") // Definuje název tabulky v MySQL
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatické generování ID (Auto Increment)
    private Long id;

    private String name;
    private double price;
    private String description;

    // Prázdný konstruktor, který Hibernate nutně vyžaduje pro načítání dat
    public Product() {}

    public Product(Long id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Gettery a Settery (ponecháme beze změny)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
