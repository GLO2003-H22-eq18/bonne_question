package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Exceptions.ItemNotFoundException;

public class SellerNotFoundException extends ItemNotFoundException {
    public SellerNotFoundException() {
        super("Seller id could not be found");
    }
}
