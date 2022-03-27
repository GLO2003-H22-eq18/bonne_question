package ulaval.glo2003.seller.ui.responses;

import java.util.List;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;

import static ulaval.glo2003.product.ui.assemblers.ProductOffersAssembler.createProductOffersResponse;

public class SellerProductResponse {
    public String id;
    public String createdAt;
    public String title;
    public String description;
    public Double suggestedPrice;
    public List<String> categories;
    public ProductOffersResponse offers;

    public SellerProductResponse() {
        super();
    }

    public SellerProductResponse(String id,
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
        this.offers = createProductOffersResponse(offers);
    }
}
