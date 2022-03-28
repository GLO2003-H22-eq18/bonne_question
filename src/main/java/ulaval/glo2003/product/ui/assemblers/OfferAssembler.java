package ulaval.glo2003.product.ui.assemblers;

import static ulaval.glo2003.product.ui.assemblers.BuyerAssembler.createBuyerResponse;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.BuyerResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

public class OfferAssembler {

    public static OfferResponse createOfferResponse(Offer offer) {
        String id = offer.getId();
        String createdAt = offer.getCreatedAt().toString();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        BuyerResponse buyer =
                createBuyerResponse(offer);

        return new OfferResponse(id, createdAt, amount, message, buyer);
    }
}
