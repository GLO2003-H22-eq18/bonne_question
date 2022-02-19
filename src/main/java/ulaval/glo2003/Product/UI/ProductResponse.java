package ulaval.glo2003.Product.UI;

import java.time.OffsetDateTime;
import java.util.List;

public class ProductResponse {
    public String id;
    public OffsetDateTime createdAt;
    public String title;
    public String description;
    public Double suggestedPrice;
    public List<String> categories;
    public ProductSellerResponse seller;
    public ProductOffersResponse offers;

    public ProductResponse(String id, OffsetDateTime createdAt, String title, String description, Double suggestedPrice,
                           List<String> categories, String sellerId, String sellerName, Double mean, Integer count) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.seller = new ProductSellerResponse(sellerId, sellerName);
        this.offers = new ProductOffersResponse(count, mean);
    }
}
