package ulaval.glo2003.seller.ui.assemblers;

import static ulaval.glo2003.product.domain.ProductCategory.toStringList;

import java.util.List;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;

public class CurrentSellerProductAssembler {

    public CurrentSellerProductResponse createCurrentSellerProductResponse(Product product) {
        String id = product.getId();
        String title = product.getTitle();
        String description = product.getDescription();
        String createdAt = product.getCreatedAt().toString();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        List<Offer> offers = product.getOffers();

        return new CurrentSellerProductResponse(id, createdAt, title, description, suggestedPrice,
                categories, offers);
    }
}