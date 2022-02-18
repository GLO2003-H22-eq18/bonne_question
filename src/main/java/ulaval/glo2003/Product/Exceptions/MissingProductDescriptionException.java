package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingProductDescriptionException extends MissingArgumentException {
    public MissingProductDescriptionException() {
        super("Product description is missing");
    }
}

