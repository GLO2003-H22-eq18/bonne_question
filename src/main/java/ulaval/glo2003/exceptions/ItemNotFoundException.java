package ulaval.glo2003.exceptions;

public abstract class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String customMsg) {
        super(customMsg);
    }
}
