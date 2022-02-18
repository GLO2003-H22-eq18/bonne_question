package ulaval.glo2003.Exceptions;

public class MissingProductTitleException extends MissingArgumentException{
    public MissingProductTitleException() {
        super("Product title is missing");
    }
}

