package ulaval.glo2003.product.infrastructure.assemblers;

import java.time.OffsetDateTime;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.infrastructure.models.OfferModel;

public class OfferModelAssembler {
    public OfferModel createOfferModel(Offer offer) {
        ObjectId id = offer.getId();
        String createdAt = offer.getCreatedAt().toString();
        Double amount = offer.getAmount();
        String message = offer.getMessage();
        String name = offer.getName();
        String email = offer.getEmail();
        String phoneNumber = offer.getPhoneNumber();

        return new OfferModel(id, createdAt, amount, message, name, email, phoneNumber);
    }

    public Offer createOffer(OfferModel offerModel) {
        ObjectId id = offerModel.getId();
        OffsetDateTime createdAt = OffsetDateTime.parse(offerModel.getCreatedAt());
        Double amount = offerModel.getAmount();
        String message = offerModel.getMessage();
        String name = offerModel.getName();
        String email = offerModel.getEmail();
        String phoneNumber = offerModel.getPhoneNumber();

        return new Offer(id, createdAt, amount, message, name, email, phoneNumber);
    }
}
