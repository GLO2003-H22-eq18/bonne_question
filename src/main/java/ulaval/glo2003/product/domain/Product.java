package ulaval.glo2003.product.domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private final String id;
    private final OffsetDateTime createdAt;
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final List<ProductCategory> categories;
    private final String sellerId;
    private final String sellerName;
    private final Integer count;
    // TODO: initialize in constructor
    private final List<Offer> offers;

    public Product(
            String id,
            String title,
            String description,
            Double suggestedPrice,
            List<ProductCategory> categories,
            String sellerId,
            String sellerName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.count = 0;
        this.offers = new ArrayList<>();

        this.createdAt = OffsetDateTime.now(Clock.systemUTC());
    }

    public String getId() {
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

    public String getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Integer getCount() {
        return count;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
