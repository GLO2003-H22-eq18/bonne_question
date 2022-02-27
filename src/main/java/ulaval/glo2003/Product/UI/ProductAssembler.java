package ulaval.glo2003.Product.UI;

import ulaval.glo2003.Product.Domain.Product;

import java.time.OffsetDateTime;
import java.util.List;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

public class ProductAssembler {

    public static ProductResponse createProductResponse(Product product) {
        String id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        ProductSellerResponse seller = new ProductSellerResponse(product.getSellerId(), product.getSellerName());
        ProductOffersResponse offers = new ProductOffersResponse(product.getCount());
        //String sellerId = product.getSellerId();
        //String sellerName = product.getSellerName();
        //Double mean = product.getMean(); pour TP3
        //Integer count = product.getCount();

        return new ProductResponse(id, createdAt, title, description, suggestedPrice,
                                    categories, seller, offers);
    }
}
