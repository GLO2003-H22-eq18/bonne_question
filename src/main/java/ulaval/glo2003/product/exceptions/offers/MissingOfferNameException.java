package ulaval.glo2003.product.exceptions.offers;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingOfferNameException extends MissingArgumentException {
    public MissingOfferNameException() {
        super("Offer name is missing");
    }
}
