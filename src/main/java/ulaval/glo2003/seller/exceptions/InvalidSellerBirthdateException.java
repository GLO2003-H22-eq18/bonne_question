package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidSellerBirthdateException extends InvalidArgumentException {
    public InvalidSellerBirthdateException() {
        super("Seller birthdate is invalid");
    }
}
