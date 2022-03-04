package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidProductCategoriesException extends InvalidArgumentException {
    public InvalidProductCategoriesException() {
        super("Product categories is invalid");
    }
}
