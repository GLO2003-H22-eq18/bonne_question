package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingOfferEmailException extends MissingArgumentException {
    public MissingOfferEmailException() {
        super("Offer email is missing");
    }
}
