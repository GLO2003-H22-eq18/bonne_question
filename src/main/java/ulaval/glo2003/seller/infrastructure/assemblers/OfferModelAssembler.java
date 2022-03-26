package ulaval.glo2003.seller.infrastructure.assemblers;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.seller.infrastructure.models.OfferModel;

public class OfferModelAssembler {
    public OfferModel createOfferModel(Offer offer) {
        String id = offer.getId();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        String name = offer.getName();
        String email = offer.getEmail();
        String phoneNumber = offer.getPhoneNumber();

        return new OfferModel(id, amount, message, name, email, phoneNumber);
    }

    public Offer createOffer(OfferModel offerModel) {
        String id = offerModel.getId();
        Double amount = offerModel.getAmount();
        String message = offerModel.getMessage();
        String name = offerModel.getName();
        String email = offerModel.getEmail();
        String phoneNumber = offerModel.getPhoneNumber();

        return new Offer(id, amount, message, name, email, phoneNumber);
    }
}
