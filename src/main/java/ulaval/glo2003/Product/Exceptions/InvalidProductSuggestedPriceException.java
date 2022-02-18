package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidProductSuggestedPriceException extends InvalidArgumentException {
    public InvalidProductSuggestedPriceException(){
        super("Product suggested price is invalid");
    }
}

