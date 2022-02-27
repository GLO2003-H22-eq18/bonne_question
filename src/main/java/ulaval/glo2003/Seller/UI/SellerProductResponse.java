package ulaval.glo2003.Seller.UI;

import ulaval.glo2003.Product.UI.ProductOffersResponse;

import java.time.OffsetDateTime;

public class SellerProductResponse {
    public String id;
    public OffsetDateTime createdAt;
    public String title;
    public String description;
    public Double suggestedPrice;
    public ProductOffersResponse offers;

    public SellerProductResponse() {
        super();
    }

    public SellerProductResponse(String id, OffsetDateTime createdAt, String title, String description,
                                 Double suggestedPrice, Integer count) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = new ProductOffersResponse(count);
    }
}
