package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidProductSuggestedPriceException extends InvalidArgumentException {
    public InvalidProductSuggestedPriceException() {
        super("Product suggested price is invalid");
    }
}
