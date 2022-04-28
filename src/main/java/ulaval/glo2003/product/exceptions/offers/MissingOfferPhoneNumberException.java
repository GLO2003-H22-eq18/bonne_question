package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingOfferPhoneNumberException extends MissingArgumentException {
    public MissingOfferPhoneNumberException() {
        super("Offer phone number is missing");
    }
}
