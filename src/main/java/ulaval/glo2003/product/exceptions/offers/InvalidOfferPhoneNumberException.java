package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidOfferPhoneNumberException extends InvalidArgumentException {
    public InvalidOfferPhoneNumberException() {
        super("Offer phone number is invalid");
    }
}
