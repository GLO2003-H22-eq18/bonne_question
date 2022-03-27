package ulaval.glo2003.seller.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import ulaval.glo2003.product.infrastructure.models.ProductModel;

import java.util.List;

@Entity("sellers")
public class SellerModel {
    @Id
    private String id;
    private String name;
    private String createdAt;
    private String bio;
    private String birthDate;
    @Reference
    private List<ProductModel> products;

    public SellerModel() {}

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