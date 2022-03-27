package ulaval.glo2003.seller.ui.responses;

import static ulaval.glo2003.product.ui.assemblers.DetailedProductOffersAssembler.createDetailedProductOffersResponse;

import java.util.List;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;

public class CurrentSellerProductResponse {

    public String id;
    public String createdAt;
    public String title;
    public String description;
    public Double suggestedPrice;
    public List<String> categories;
    public DetailedProductOffersResponse offers;

    public CurrentSellerProductResponse() {
        super();
    }

    public CurrentSellerProductResponse(String id,
                                        String createdAt,
                                        String title,
                                        String description,
                                        Double suggestedPrice,
                                        List<String> categories,
                                        List<Offer> offers) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.categories = categories;
        this.offers = createDetailedProductOffersResponse(offers);
    }
}
