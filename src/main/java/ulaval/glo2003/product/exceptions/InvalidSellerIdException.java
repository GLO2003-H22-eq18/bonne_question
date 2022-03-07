package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidSellerIdException extends InvalidArgumentException {
    public InvalidSellerIdException() {
        super("Seller id is invalid");
    }
}
