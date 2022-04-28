package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidOfferEmailException extends InvalidArgumentException {
    public InvalidOfferEmailException() {
        super("Offer email is invalid");
    }
}
