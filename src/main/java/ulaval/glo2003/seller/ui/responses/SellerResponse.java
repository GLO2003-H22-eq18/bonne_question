package ulaval.glo2003.seller.ui.responses;

import java.util.List;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;

public class SellerResponse {
    public String id;
    public String createdAt;
    public String name;
    public String bio;
    public List<SellerProductResponse> products;

    public SellerResponse() {
        super();
    }

    public SellerResponse(String id,
                          String createdAt,
                          String name,
                          String bio,
                          List<SellerProductResponse> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.products = products;
    }
}
