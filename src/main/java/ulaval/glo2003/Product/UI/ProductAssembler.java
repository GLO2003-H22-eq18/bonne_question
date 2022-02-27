package ulaval.glo2003.Product.UI;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

import java.time.OffsetDateTime;
import java.util.List;
import ulaval.glo2003.Product.Domain.Product;

public class ProductAssembler {

    public ProductResponse createProductResponse(Product product) {
        String id = product.getId();
        OffsetDateTime createdAt = product.getCreatedAt();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        String sellerId = product.getSellerId();
        String sellerName = product.getSellerName();
        Double mean = product.getMean();
        Integer count = product.getCount();

        return new ProductResponse(
                id,
                createdAt,
                title,
                description,
                suggestedPrice,
                categories,
                sellerId,
                sellerName,
                mean,
                count);
    }
}
