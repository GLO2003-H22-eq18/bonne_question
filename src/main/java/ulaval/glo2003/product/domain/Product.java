package ulaval.glo2003.product.domain;

import java.time.OffsetDateTime;
import java.util.List;
import org.bson.types.ObjectId;

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
    private final List<View> views;

    public Product(
            String title,
            String description,
            Double suggestedPrice,
            List<ProductCategory> categories,
            ObjectId sellerId,
            String sellerName,
            ObjectId id,
            List<Offer> offers,
            List<View> views,
            OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.offers = offers;
        this.views = views;
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

    public List<View> getViews() {
        return views;
    }

    public void addView(View view) {
        this.views.add(view);
    }
}
