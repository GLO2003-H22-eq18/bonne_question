package ulaval.glo2003.product.ui;

import static ulaval.glo2003.product.domain.ProductCategory.toStringList;
import static ulaval.glo2003.product.ui.ProductOffersAssembler.createProductOffersResponse;
import static ulaval.glo2003.product.ui.ProductSellerAssembler.createProductSellerResponse;

import java.util.List;
import ulaval.glo2003.product.domain.Product;

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
