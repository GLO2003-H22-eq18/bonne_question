package ulaval.glo2003.Exceptions;

public class MissingProductDescriptionException extends MissingArgumentException{
    public MissingProductDescriptionException() {
        super("Product description is missing");
    }
}

