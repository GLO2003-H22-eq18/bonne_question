package ulaval.glo2003.product.ui.assemblers;

import java.util.List;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;

public class ProductOffersAssembler {

    public ProductOffersResponse createProductOffersResponse(List<Offer> offers) {
        Double mean = getOffersMean(offers);
        Integer count = getOffersCount(offers);
        return new ProductOffersResponse(mean, count);
    }

    private Double getOffersMean(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double total = 0.0;
        for (Offer offer : offers) {
            total += offer.getAmount();
        }
        return (Math.round((total / offers.size()) * 100.0) / 100.0);
    }

    private Integer getOffersCount(List<Offer> offers) {
        return offers.size();
    }
}
