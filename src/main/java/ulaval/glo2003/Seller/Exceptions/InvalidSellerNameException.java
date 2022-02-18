package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidSellerNameException extends InvalidArgumentException {
    public InvalidSellerNameException(){
        super("Seller name is invalid");
    }
}
