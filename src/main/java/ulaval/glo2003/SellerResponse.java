package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.List;

public class SellerResponse {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;
    public List<String> products;

    public SellerResponse(Seller seller) {
        this.id = seller.getId();
        this.createdAt = seller.getCreatedAt();
        this.name = seller.getName();
        this.bio = seller.getBio();
        this.products = seller.getProducts();
    }
}
