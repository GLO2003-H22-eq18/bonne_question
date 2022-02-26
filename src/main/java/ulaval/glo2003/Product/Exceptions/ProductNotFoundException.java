package ulaval.glo2003.Product.Exceptions;

import ulaval.glo2003.Exceptions.ItemNotFoundException;

public class ProductNotFoundException extends ItemNotFoundException {
  public ProductNotFoundException() {
    super("Product ID could not be found");
  }
}
