package ulaval.glo2003;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

public class Product {
    public String id;
    public OffsetDateTime createdAt;
    public String title;
    public String description;
    public Double suggestedPrice;
    public List<String> categories;
    public String sellerId;
    public String sellerName;
    public Double mean; // Amount
    public Integer count;
    private static int currentId = 0;


    public Product(String title, String description, Double suggestedPrice,
                   List<String> categories, String sellerId, String sellerName){
        this.id = String.valueOf(currentId++);
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.count = 0;

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

    public Double getSuggestedPrice() { return suggestedPrice; }

    public List<String> getCategories(){ return categories; }

    public String getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getMean() {
        return mean;
    }

    public Integer getCount(){ return count; }
}
