package ulaval.glo2003.Exceptions;

public class InvalidProductSuggestedPriceException extends InvalidArgumentException{
    public InvalidProductSuggestedPriceException(){
        super("Product description is invalid");
    }
}

