package ulaval.glo2003.product.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.util.List;
import org.bson.types.ObjectId;

@Entity("products")
public class ProductModel {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private String createdAt;
    private Double suggestedPrice;
    private List<String> categories;
    private ObjectId sellerId;
    private String sellerName;
    private List<OfferModel> offers;
    private List<ViewModel> views;

    public ProductModel() {
    }

    public ProductModel(
            String title,
            String description,
            String createdAt,
            Double suggestedPrice,
            List<String> categories,
            ObjectId sellerId,
            String sellerName,
            ObjectId id,
            List<OfferModel> offers,
            List<ViewModel> views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.createdAt = createdAt;
        this.offers = offers;
        this.views = views;
    }

    public ObjectId getId() {
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

    public ObjectId getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public List<OfferModel> getOffers() {
        return offers;
    }

    public List<ViewModel> getViews() {
        return views;
    }
}
