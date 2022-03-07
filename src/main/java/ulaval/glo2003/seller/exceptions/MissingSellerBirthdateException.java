package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingSellerBirthdateException extends MissingArgumentException {
    public MissingSellerBirthdateException() {
        super("Seller birthdate is missing");
    }
}
