package ulaval.glo2003.Seller.UI;

import ulaval.glo2003.Product.UI.ProductOffersResponse;

import java.time.OffsetDateTime;

public class SellerProductResponse {
    public final String id;
    public final OffsetDateTime createdAt;
    public final String title;
    public final String description;
    public final Double suggestedPrice;
    public final ProductOffersResponse offers;

    public SellerProductResponse(String id, OffsetDateTime createdAt, String title, String description,
                                 Double suggestedPrice, Integer count) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.offers = new ProductOffersResponse(count); // Ajouter mean pour TP3
    }
}
