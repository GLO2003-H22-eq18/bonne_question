package ulaval.glo2003.seller.ui.assemblers;

import static ulaval.glo2003.product.domain.ProductCategory.toStringList;

import java.util.List;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.ui.assemblers.DetailedProductOffersAssembler;
import ulaval.glo2003.product.ui.assemblers.DetailedProductViewsAssembler;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.product.ui.responses.DetailedProductViewsResponse;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;

public class CurrentSellerProductAssembler {

    private final DetailedProductOffersAssembler detailedProductOffersAssembler;
    private final DetailedProductViewsAssembler detailedProductViewsAssembler;

    public CurrentSellerProductAssembler() {
        detailedProductOffersAssembler = new DetailedProductOffersAssembler();
        detailedProductViewsAssembler = new DetailedProductViewsAssembler();
    }

    public CurrentSellerProductResponse createCurrentSellerProductResponse(Product product) {
        String id = product.getId().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        String createdAt = product.getCreatedAt().toString();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        DetailedProductOffersResponse offers =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(
                        product.getOffers());

        return new CurrentSellerProductResponse(id, createdAt, title, description, suggestedPrice,
                categories, offers);
    }

    public CurrentSellerProductResponse createCurrentSellerProductViewsResponse(Product product) {
        String id = product.getId().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        String createdAt = product.getCreatedAt().toString();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        DetailedProductViewsResponse views =
                detailedProductViewsAssembler.createDetailedProductViewsResponse(
                        product.getViews());

        return new CurrentSellerProductResponse(id, createdAt, title, description, suggestedPrice,
                categories, views);
    }
}
