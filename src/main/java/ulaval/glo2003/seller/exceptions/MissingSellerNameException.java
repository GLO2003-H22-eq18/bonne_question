package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingSellerNameException extends MissingArgumentException {
    public MissingSellerNameException() {
        super("Seller name is missing");
    }
}
