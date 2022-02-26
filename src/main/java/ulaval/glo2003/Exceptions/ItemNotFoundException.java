package ulaval.glo2003.Exceptions;

public abstract class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(String customMsg) {
    super(customMsg);
  }
}
