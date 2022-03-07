package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidSellerIdException extends InvalidArgumentException {
    public InvalidSellerIdException() {
        super("Seller id is invalid");
    }
}
