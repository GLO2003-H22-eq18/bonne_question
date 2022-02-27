package ulaval.glo2003.Seller.UI;

import ulaval.glo2003.Product.Domain.Product;

import java.time.OffsetDateTime;
import java.util.List;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

public class SellerProductAssembler {

    public static SellerProductResponse createSellerProductResponse(Product product) {
        String id = product.getId();
        OffsetDateTime createdAt = product.getCreatedAt();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        //Double mean = product.getMean(); ajouter pour TP3
        Integer count = product.getCount();

        return new SellerProductResponse(id, createdAt, title, description, suggestedPrice,
                count); // ajouter mean pour TP3
    }
}
