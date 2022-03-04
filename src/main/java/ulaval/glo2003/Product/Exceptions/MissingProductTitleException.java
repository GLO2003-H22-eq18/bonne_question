package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingProductTitleException extends MissingArgumentException {
    public MissingProductTitleException() {
        super("Product title is missing");
    }
}
