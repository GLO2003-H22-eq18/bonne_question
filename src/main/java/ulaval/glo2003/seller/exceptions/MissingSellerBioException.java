package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.MissingArgumentException;

public class MissingSellerBioException extends MissingArgumentException {
    public MissingSellerBioException() {
        super("Seller bio is missing");
    }
}
