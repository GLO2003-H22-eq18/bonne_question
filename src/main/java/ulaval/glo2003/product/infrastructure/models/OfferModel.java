package ulaval.glo2003.product.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("offers")
public class OfferModel {
    @Id
    private ObjectId id;
    private String createdAt;
    private Double amount;
    private String message;
    private String name;
    private String email;
    private String phoneNumber;

    public OfferModel() {
    }

    public OfferModel(
            ObjectId id,
            String createdAt,
            Double amount,
            String message,
            String name,
            String email,
            String phoneNumber) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.message = message;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Double getAmount() {
        return amount;
    }

    public ObjectId getId() {
        return id;
    }
}