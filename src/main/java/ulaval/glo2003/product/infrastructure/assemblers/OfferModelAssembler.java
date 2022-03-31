package ulaval.glo2003.product.infrastructure.assemblers;

import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.infrastructure.models.OfferModel;

public class OfferModelAssembler {
    public OfferModel createOfferModel(Offer offer) {
        ObjectId id = offer.getId();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        String name = offer.getName();
        String email = offer.getEmail();
        String phoneNumber = offer.getPhoneNumber();

        return new OfferModel(id, amount, message, name, email, phoneNumber);
    }

    public Offer createOffer(OfferModel offerModel) {
        ObjectId id = offerModel.getId();
        Double amount = offerModel.getAmount();
        String message = offerModel.getMessage();
        String name = offerModel.getName();
        String email = offerModel.getEmail();
        String phoneNumber = offerModel.getPhoneNumber();

        return new Offer(id, amount, message, name, email, phoneNumber);
    }
}
