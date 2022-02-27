package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidSellerBirthdateException extends InvalidArgumentException {
    public InvalidSellerBirthdateException() {
        super("Seller birthdate is invalid");
    }
}
