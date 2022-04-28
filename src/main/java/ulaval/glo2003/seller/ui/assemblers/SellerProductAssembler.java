package ulaval.glo2003.seller.ui.assemblers;

import static ulaval.glo2003.product.domain.ProductCategory.toStringList;

import java.util.List;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.ui.assemblers.ProductOffersAssembler;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;

public class SellerProductAssembler {

    private final ProductOffersAssembler productOffersAssembler;

    public SellerProductAssembler() {
        productOffersAssembler = new ProductOffersAssembler();
    }

    public SellerProductResponse createSellerProductResponse(Product product) {
        String id = product.getId().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        String createdAt = product.getCreatedAt().toString();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        ProductOffersResponse offers =
                productOffersAssembler.createProductOffersResponse(product.getOffers());

        return new SellerProductResponse(id, createdAt, title, description, suggestedPrice,
                categories, offers);
    }
}
