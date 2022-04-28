package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.BuyerResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

public class OfferAssembler {

    private final BuyerAssembler buyerAssembler;

    public OfferAssembler() {
        buyerAssembler = new BuyerAssembler();
    }

    public OfferResponse createOfferResponse(Offer offer) {
        String id = offer.getId().toString();
        String createdAt = offer.getCreatedAt().toString();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        BuyerResponse buyer =
                buyerAssembler.createBuyerResponse(offer);

        return new OfferResponse(id, createdAt, amount, message, buyer);
    }
}
