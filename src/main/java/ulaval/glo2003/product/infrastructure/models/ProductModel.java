package ulaval.glo2003.product.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.List;

@Entity("products")
public class ProductModel {
    @Id
    private String id;
    private String title;
    private String description;
    private String createdAt;
    private Double suggestedPrice;
    private List<String> categories;
    private String sellerId;
    private String sellerName;
    private Integer count;
    private List<OfferModel> offers;

    public ProductModel() {}

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
