package ulaval.glo2003.Product.UI;

import ulaval.glo2003.Product.Domain.Product;

import java.time.OffsetDateTime;
import java.util.List;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;
import static ulaval.glo2003.Product.UI.ProductOffersAssembler.createProductOffersResponse;
import static ulaval.glo2003.Product.UI.ProductSellerAssembler.createProductSellerResponse;

public class ProductAssembler {

    public static ProductResponse createProductResponse(Product product) {
        String id = product.getId();
        OffsetDateTime createdAt = product.getCreatedAt();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        ProductSellerResponse seller = createProductSellerResponse(product.getSellerId(), product.getSellerName());
        ProductOffersResponse offers = createProductOffersResponse(product.getCount());

        return new ProductResponse(id, createdAt, title, description, suggestedPrice,
                                    categories, seller, offers);
    }
}
