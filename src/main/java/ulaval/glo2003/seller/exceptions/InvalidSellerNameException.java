package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidSellerNameException extends InvalidArgumentException {
    public InvalidSellerNameException() {
        super("Seller name is invalid");
    }
}
