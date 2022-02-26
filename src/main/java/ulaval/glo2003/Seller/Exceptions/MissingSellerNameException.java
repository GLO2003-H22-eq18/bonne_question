package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingSellerNameException extends MissingArgumentException {
  public MissingSellerNameException() {
    super("Seller name is missing");
  }
}
