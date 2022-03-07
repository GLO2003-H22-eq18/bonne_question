package ulaval.glo2003.exceptions;

public abstract class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String customMsg) {
        super(customMsg);
    }
}
