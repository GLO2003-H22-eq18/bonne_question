package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.responses.BuyerResponse;

public class BuyerAssembler {

    public BuyerResponse createBuyerResponse(Offer offer) {
        String name = offer.getName();
        String email = offer.getEmail();
        String phoneNumber = offer.getPhoneNumber();

        return new BuyerResponse(name, email, phoneNumber);
    }

}
