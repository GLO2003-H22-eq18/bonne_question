package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidProductTitleException extends InvalidArgumentException {
  public InvalidProductTitleException() {
    super("Product title is invalid");
  }
}
