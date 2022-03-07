package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingProductSuggestedPriceException extends MissingArgumentException {
    public MissingProductSuggestedPriceException() {
        super("Product suggested price is missing");
    }
}
