package ulaval.glo2003.Exceptions;

public class InvalidProductDescriptionException extends InvalidArgumentException{
    public InvalidProductDescriptionException(){
        super("Product description is invalid");
    }
}

