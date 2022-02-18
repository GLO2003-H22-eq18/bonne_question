package ulaval.glo2003.Exceptions;

public class MissingProductCategoriesException extends MissingArgumentException{
    public MissingProductCategoriesException() {
        super("Product categories field is missing");
    }
}

