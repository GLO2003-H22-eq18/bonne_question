package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingOfferAmountException extends MissingArgumentException {
    public MissingOfferAmountException() {
        super("Offer amount is missing");
    }
}
