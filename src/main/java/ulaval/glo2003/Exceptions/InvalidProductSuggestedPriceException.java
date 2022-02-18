package ulaval.glo2003.Exceptions;

public class InvalidProductSuggestedPriceException extends InvalidArgumentException{
    public InvalidProductSuggestedPriceException(){
        super("Product suggested price is invalid");
    }
}

