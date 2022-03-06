package ulaval.glo2003.Product.UI;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;
import static ulaval.glo2003.Product.UI.ProductOffersAssembler.createProductOffersResponse;
import static ulaval.glo2003.Product.UI.ProductSellerAssembler.createProductSellerResponse;

import java.util.List;
import ulaval.glo2003.Product.Domain.Product;

public class ProductAssembler {

    public ProductResponse createProductResponse(Product product) {
        String id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        ProductSellerResponse seller =
                createProductSellerResponse(product.getSellerId(), product.getSellerName());
        ProductOffersResponse offers = createProductOffersResponse(product.getCount());

        return new ProductResponse(id, createdAt, title, description, suggestedPrice, categories,
                seller, offers);
    }
}
