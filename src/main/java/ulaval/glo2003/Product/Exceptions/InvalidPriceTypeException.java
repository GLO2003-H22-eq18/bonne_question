package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidPriceTypeException extends InvalidArgumentException {
    public InvalidPriceTypeException() {
        super("Price type is invalid.");
    }
}
