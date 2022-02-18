package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.List;

public class ProductResponse {
    String id;
    OffsetDateTime createdAt;
    String title;
    String description;
    Double suggestedPrice;
    List<String> categories;
    String sellerId;
    String sellerName;
    Double mean;
    Integer count;

    public ProductResponse(String id, OffsetDateTime createdAt, String title, String description, Double suggestedPrice,
                           List<String> categories, String sellerId, String sellerName, Double mean, Integer count) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.mean = mean;
        this.count = count;
    }
}
