package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingProductSuggestedPriceException extends MissingArgumentException {
    public MissingProductSuggestedPriceException() {
        super("Product suggested price is missing");
    }
}
