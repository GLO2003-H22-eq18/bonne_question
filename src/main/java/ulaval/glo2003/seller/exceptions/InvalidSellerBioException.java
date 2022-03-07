package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.InvalidArgumentException;

public class InvalidSellerBioException extends InvalidArgumentException {
    public InvalidSellerBioException() {
        super("Seller bio is invalid");
    }
}
