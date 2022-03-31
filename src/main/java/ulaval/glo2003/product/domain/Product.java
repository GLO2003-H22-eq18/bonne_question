package ulaval.glo2003.product.domain;

import org.bson.types.ObjectId;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private final ObjectId id;
    private final OffsetDateTime createdAt;
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final List<ProductCategory> categories;
    private final ObjectId sellerId;
    private final String sellerName;
    private final List<Offer> offers;

    public Product(
            String title,
            String description,
            Double suggestedPrice,
            List<ProductCategory> categories,
            ObjectId sellerId,
            String sellerName,
            ObjectId id,
            List<Offer> offers,
            OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.offers = offers;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public ObjectId getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer) {
        this.offers.add(offer);
    }
}
