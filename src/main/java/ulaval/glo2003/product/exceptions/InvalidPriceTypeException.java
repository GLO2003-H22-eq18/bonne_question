package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidPriceTypeException extends InvalidArgumentException {
    public InvalidPriceTypeException() {
        super("Price type is invalid.");
    }
}
