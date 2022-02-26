package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.InvalidArgumentException;

public class InvalidSellerBioException extends InvalidArgumentException {
  public InvalidSellerBioException() {
    super("Seller bio is invalid");
  }
}
