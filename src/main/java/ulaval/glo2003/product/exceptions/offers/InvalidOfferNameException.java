package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidOfferNameException extends InvalidArgumentException {
    public InvalidOfferNameException() {
        super("Offer name is invalid");
    }
}
