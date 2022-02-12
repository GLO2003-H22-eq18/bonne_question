package ulaval.glo2003.Exceptions;

public abstract class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String customMsg){
        super(customMsg);
    }
}
