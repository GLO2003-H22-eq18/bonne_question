package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidProductCategoriesException extends InvalidArgumentException {
    public InvalidProductCategoriesException() {
        super("Product categories is invalid");
    }
}
