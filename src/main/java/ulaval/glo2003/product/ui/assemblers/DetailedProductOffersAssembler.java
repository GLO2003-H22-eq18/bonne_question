package ulaval.glo2003.product.ui.assemblers;

import static java.lang.Double.MAX_VALUE;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

public class DetailedProductOffersAssembler {

    private final OfferAssembler offerAssembler;

    public DetailedProductOffersAssembler() {
        offerAssembler = new OfferAssembler();
    }

    public DetailedProductOffersResponse createDetailedProductOffersResponse(
            List<Offer> offers) {
        Double min = getOffersMin(offers);
        Double max = getOffersMax(offers);
        Double mean = getOffersMean(offers);
        Integer count = getOfferCount(offers);

        List<OfferResponse> offersResponse = offers.stream()
                .map(offerAssembler::createOfferResponse)
                .collect(Collectors.toList());

        return new DetailedProductOffersResponse(min, max, mean, count, offersResponse);
    }

    private Double getOffersMin(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double min = MAX_VALUE;
        for (Offer offer : offers) {
            if (offer.getAmount() < min) {
                min = offer.getAmount();
            }
        }
        return (Math.round(min * 100.0) / 100.0);
    }

    private Double getOffersMax(List<Offer> offers) {
        if (offers.isEmpty()) {
            return null;
        }
        Double max = 0.0;
        for (Offer offer : offers) {
            if (offer.getAmount() > max) {
                max = offer.getAmount();
            }
        }
        return (Math.round(max * 100.0) / 100.0);
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

    private Integer getOfferCount(List<Offer> offers) {
        return offers.size();
    }
}
