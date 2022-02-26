package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingSellerBirthdateException extends MissingArgumentException {
  public MissingSellerBirthdateException() {
    super("Seller birthdate is missing");
  }
}
