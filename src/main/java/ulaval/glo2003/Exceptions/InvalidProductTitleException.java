package ulaval.glo2003.Exceptions;

public class InvalidProductTitleException extends InvalidArgumentException{
    public InvalidProductTitleException(){
        super("Product title is invalid");
    }
}

