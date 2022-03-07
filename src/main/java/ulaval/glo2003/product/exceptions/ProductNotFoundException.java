package ulaval.glo2003.product.exceptions;

import ulaval.glo2003.exceptions.ItemNotFoundException;

public class ProductNotFoundException extends ItemNotFoundException {
    public ProductNotFoundException() {
        super("Product ID could not be found");
    }
}
