package ulaval.glo2003.product.ui.assemblers;

import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.BuyerResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

public class OfferAssembler {

    private final BuyerAssembler buyerAssembler;

    public OfferAssembler() {
        buyerAssembler = new BuyerAssembler();
    }

    public OfferResponse createOfferResponse(Offer offer) {
        ObjectId id = offer.getId();
        String createdAt = offer.getCreatedAt().toString();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        BuyerResponse buyer =
                buyerAssembler.createBuyerResponse(offer);

        return new OfferResponse(id, createdAt, amount, message, buyer);
    }
}
