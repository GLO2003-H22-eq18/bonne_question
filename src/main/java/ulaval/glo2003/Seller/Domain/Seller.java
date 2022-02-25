package ulaval.glo2003.Seller.Domain;

import ulaval.glo2003.Product.Domain.Product;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public class Seller {
    private String id;
    private OffsetDateTime createdAt;
    private LocalDate birthDate;
    private String name;
    private String bio;
    private List<Product> products;

    private static int currentId = 0;

    public Seller(String name, String bio, OffsetDateTime createdAt, LocalDate birthDate, List<Product> products) {
        this.id = String.valueOf(currentId++);
        this.createdAt = createdAt;
        this.birthDate = birthDate;
        this.name = name;
        this.bio = bio;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDate getBirthDate() { return birthDate; }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
