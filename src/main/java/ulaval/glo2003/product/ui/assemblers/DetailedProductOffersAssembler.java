package ulaval.glo2003.product.ui.assemblers;

import static java.lang.Double.MAX_VALUE;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

public class DetailedProductOffersAssembler {

    public static DetailedProductOffersResponse createDetailedProductOffersResponse(
            List<Offer> offers) {
        Double min = getOffersMin(offers);
        Double max = getOffersMax(offers);
        Double mean = getOffersMean(offers);
        Integer count = getOfferCount(offers);

        List<OfferResponse> offersResponse = offers.stream()
                .map(OfferAssembler::createOfferResponse)
                .collect(Collectors.toList());

        return new DetailedProductOffersResponse(min, max, mean, count, offersResponse);
    }

    private static Double getOffersMin(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double min = MAX_VALUE;
        for (Offer offer : offers) {
            if (offer.getAmount() < min) {
                min = offer.getAmount();
            }
        }
        return min;
    }

    private static Double getOffersMax(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double max = 0.0;
        for (Offer offer : offers) {
            if (offer.getAmount() > max) {
                max = offer.getAmount();
            }
        }
        return max;
    }

    private static Double getOffersMean(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double total = 0.0;
        for (Offer offer : offers) {
            total += offer.getAmount();
        }
        return total / offers.size();
    }

    private static Integer getOfferCount(List<Offer> offers) {
        return offers.size();
    }
}
