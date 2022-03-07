package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingProductTitleException extends MissingArgumentException {
    public MissingProductTitleException() {
        super("Product title is missing");
    }
}
