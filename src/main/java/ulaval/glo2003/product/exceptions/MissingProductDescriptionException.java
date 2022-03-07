package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingProductDescriptionException extends MissingArgumentException {
    public MissingProductDescriptionException() {
        super("Product description is missing");
    }
}
