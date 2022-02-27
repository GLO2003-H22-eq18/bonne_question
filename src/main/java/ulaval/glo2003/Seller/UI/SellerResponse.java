package ulaval.glo2003.Seller.UI;

import java.time.OffsetDateTime;
import java.util.List;

public class SellerResponse {
    public final String id;
    public final OffsetDateTime createdAt;
    public final String name;
    public final String bio;
    public final List<SellerProductResponse> products;

    public SellerResponse(String id, OffsetDateTime createdAt, String name, String bio, List<SellerProductResponse> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.products = products;
    }
}
