package ulaval.glo2003.seller.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("offers")
public class OfferModel {
    @Id
    private final String id;
    private final Double amount;
    private final String message;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public OfferModel(
            String id,
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

    public String getId() {
        return id;
    }
}