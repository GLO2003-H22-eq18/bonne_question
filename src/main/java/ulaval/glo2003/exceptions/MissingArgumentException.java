package ulaval.glo2003.exceptions;

public abstract class MissingArgumentException extends RuntimeException {
    public MissingArgumentException(String customMsg) {
        super(customMsg);
    }
}
