package ulaval.glo2003.Seller.UI;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

import java.time.OffsetDateTime;
import java.util.List;
import ulaval.glo2003.Product.Domain.Product;

public class SellerProductAssembler {

    public SellerProductResponse createSellerProductResponse(Product product) {
        String id = product.getId();
        OffsetDateTime createdAt = product.getCreatedAt();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        Double mean = product.getMean();
        Integer count = product.getCount();
        List<String> categories = toStringList(product.getCategories());

        return new SellerProductResponse(
                id, createdAt, title, description, suggestedPrice, mean, count, categories);
    }
}
