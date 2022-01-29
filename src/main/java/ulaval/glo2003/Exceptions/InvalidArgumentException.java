package ulaval.glo2003.Exceptions;

public abstract class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String customMsg) {
        super(customMsg);
    }
}
