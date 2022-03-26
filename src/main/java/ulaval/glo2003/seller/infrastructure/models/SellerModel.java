package ulaval.glo2003.seller.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import dev.morphia.mapping.experimental.MorphiaReference;

import java.util.List;

@Entity("sellers")
public class SellerModel {
    @Id
    private final String id;
    private final String name;
    private final String createdAt;
    private final String bio;
    private final String birthDate;
    @Reference
    private final List<ProductModel> products;

    public SellerModel(
            String id,
            String createdAt,
            String birthDate,
            String name,
            String bio,
            List<ProductModel> products) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public List<ProductModel> getProducts() {
        return products;
    }
}