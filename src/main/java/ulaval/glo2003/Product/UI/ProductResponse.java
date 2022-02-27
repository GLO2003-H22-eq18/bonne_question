package ulaval.glo2003.Product.UI;

import java.time.OffsetDateTime;
import java.util.List;

public class ProductResponse {
    public final String id;
    public final String createdAt;
    public final String title;
    public final String description;
    public final Double suggestedPrice;
    public final List<String> categories;
    public final ProductSellerResponse seller;
    public final ProductOffersResponse offers;

    public ProductResponse(String id, String createdAt, String title, String description, Double suggestedPrice,
                           List<String> categories, ProductSellerResponse seller, ProductOffersResponse offers) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.seller = seller;
        this.offers = offers;
    }
}
