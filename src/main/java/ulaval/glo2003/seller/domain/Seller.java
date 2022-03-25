package ulaval.glo2003.seller.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import ulaval.glo2003.product.domain.Product;

public class Seller {
    private final String id;
    private final OffsetDateTime createdAt;
    private final LocalDate birthDate;
    private final String name;
    private final String bio;
    private final List<Product> products;

    public Seller(
            String id,
            String name,
            String bio,
            OffsetDateTime createdAt,
            LocalDate birthDate,
            List<Product> products) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

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
