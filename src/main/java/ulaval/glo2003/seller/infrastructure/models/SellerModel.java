package ulaval.glo2003.seller.infrastructure.models;


import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.util.List;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.infrastructure.models.ProductModel;

@Entity("sellers")
public class SellerModel {
    @Id
    private ObjectId id;
    private String name;
    private String createdAt;
    private String bio;
    private String birthDate;
    @Reference
    private List<ProductModel> products;

    public SellerModel() {
    }

    public SellerModel(
            ObjectId id,
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

    public ObjectId getId() {
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