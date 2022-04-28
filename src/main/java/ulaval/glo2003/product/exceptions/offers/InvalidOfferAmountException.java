package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidOfferAmountException extends InvalidArgumentException {
    public InvalidOfferAmountException() {
        super("Offer amount is invalid");
    }
}
