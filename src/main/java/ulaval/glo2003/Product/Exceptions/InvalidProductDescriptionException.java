package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidProductDescriptionException extends InvalidArgumentException {
    public InvalidProductDescriptionException() {
        super("Product description is invalid");
    }
}
