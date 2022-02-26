package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.MissingArgumentException;

public class MissingSellerBioException extends MissingArgumentException {
  public MissingSellerBioException() {
    super("Seller bio is missing");
  }
}
