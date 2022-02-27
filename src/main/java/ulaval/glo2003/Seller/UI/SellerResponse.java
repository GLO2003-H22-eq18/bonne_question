package ulaval.glo2003.Seller.UI;

import java.time.OffsetDateTime;
import java.util.List;

public class SellerResponse {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;
    public List<SellerProductResponse> products;

    public SellerResponse() {
        super();
    }

    public SellerResponse(String id, OffsetDateTime createdAt, String name, String bio, List<SellerProductResponse> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.products = products;
    }
}
