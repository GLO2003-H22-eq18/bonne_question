package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidProductTitleException extends InvalidArgumentException {
    public InvalidProductTitleException() {
        super("Product title is invalid");
    }
}
