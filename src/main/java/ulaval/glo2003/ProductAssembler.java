package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.List;

public class ProductAssembler {

    public ProductResponse createProductResponse(Product product) {
        String id = product.getId();
        OffsetDateTime createdAt = product.getCreatedAt();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = product.getCategories();
        String sellerId = product.getSellerId();
        String sellerName = product.getSellerName();
        Double mean = product.getMean();
        Integer count = product.getCount();

        return new ProductResponse(...);
    }
}
