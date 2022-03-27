package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;

import java.util.List;

public class ProductOffersAssembler {

    public static ProductOffersResponse createProductOffersResponse(List<Offer> offers) {
        Double mean = getOffersMean(offers);
        Integer count = getOffersCount(offers);
        return new ProductOffersResponse(mean, count);
    }

    private static Double getOffersMean (List<Offer> offers){
        if(offers.isEmpty()){
            return null;
        }
        Double total = 0.0;
        for(int i = 0; i < offers.size(); i++){
            total += offers.get(i).getAmount();
        }
        return total/offers.size();
    }

    private static Integer getOffersCount (List<Offer> offers){
        return offers.size();
    }
}
