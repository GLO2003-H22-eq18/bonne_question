package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingOfferMessageException extends MissingArgumentException {
    public MissingOfferMessageException() {
        super("Offer message is missing");
    }
}
