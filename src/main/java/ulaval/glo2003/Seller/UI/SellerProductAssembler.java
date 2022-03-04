package ulaval.glo2003.Seller.UI;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

import java.util.List;
import ulaval.glo2003.Product.Domain.Product;

public class SellerProductAssembler {

    public SellerProductResponse createSellerProductResponse(Product product) {
        String id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        Integer count = product.getCount();

        return new SellerProductResponse(id, createdAt, title, description, suggestedPrice, categories, count);
    }
}
