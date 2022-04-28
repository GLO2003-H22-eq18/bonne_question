package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidOfferMessageException extends InvalidArgumentException {
    public InvalidOfferMessageException() {
        super("Offer message is invalid");
    }
}
