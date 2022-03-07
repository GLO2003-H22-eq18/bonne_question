package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidProductDescriptionException extends InvalidArgumentException {
    public InvalidProductDescriptionException() {
        super("Product description is invalid");
    }
}
