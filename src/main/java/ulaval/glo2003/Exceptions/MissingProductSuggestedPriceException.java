package ulaval.glo2003.Exceptions;

public class MissingProductSuggestedPriceException extends MissingArgumentException{
    public MissingProductSuggestedPriceException() {
        super("Product suggested price is missing");
    }
}

