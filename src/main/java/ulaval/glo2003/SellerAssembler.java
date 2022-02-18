package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.List;

public class SellerAssembler {
    public SellerAssembler() {}

    public SellerResponse createSellerResponse(Seller seller) {
        String id = seller.getId();
        OffsetDateTime createdAt = seller.getCreatedAt();
        String name = seller.getName();
        String bio = seller.getBio();
        List<String> products = seller.getProducts();

        SellerResponse sellerResponse = new SellerResponse(id, createdAt, name, bio, products);

        return sellerResponse;
    }
}
