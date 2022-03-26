package ulaval.glo2003.seller.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.mapping.experimental.MorphiaReference;

import java.util.List;

@Entity("products")
public class ProductModel {
    @Id
    private final String id;
    private final String title;
    private final String description;
    private final String createdAt;
    private final Double suggestedPrice;
    private final List<String> categories;
    private final String sellerId;
    private final String sellerName;
    private final Integer count;
    private final List<OfferModel> offers;

    public ProductModel(
            String id,
            String title,
            String description,
            String createdAt,
            Double suggestedPrice,
            List<String> categories,
            String sellerId,
            String sellerName,
            List<OfferModel> offers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.count = 0;
        this.createdAt = createdAt;
        this.offers = offers;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
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

    public List<String> getCategories() {
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

    public List<OfferModel> getOffers() {
        return offers;
    }
}
