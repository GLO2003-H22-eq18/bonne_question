package ulaval.glo2003.Exceptions;

public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String customMsg){
        super(customMsg);
    }
}
