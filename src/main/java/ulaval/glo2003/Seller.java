package ulaval.glo2003;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

public class Seller {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;
    public List<String> products;
    private static int currentId = 0;

    public Seller(String name, String bio, List<String> products) {
        this.id = String.valueOf(currentId++);
        this.createdAt = OffsetDateTime.now(Clock.systemUTC());;
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

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getProducts() {
        return products;
    }
}
