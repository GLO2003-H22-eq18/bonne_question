package ulaval.glo2003.product.domain;

import java.time.OffsetDateTime;
import org.bson.types.ObjectId;

public class Offer {
    private final ObjectId id;
    private final OffsetDateTime createdAt;
    private final Double amount;
    private final String message;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public Offer(
            ObjectId id,
            OffsetDateTime createdAt,
            Double amount,
            String message,
            String name,
            String email,
            String phoneNumber) {
        this.id = id;
        this.amount = amount;
        this.message = message;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
