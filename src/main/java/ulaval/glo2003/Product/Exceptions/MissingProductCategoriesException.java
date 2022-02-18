package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingProductCategoriesException extends MissingArgumentException {
    public MissingProductCategoriesException() {
        super("Product categories field is missing");
    }
}

