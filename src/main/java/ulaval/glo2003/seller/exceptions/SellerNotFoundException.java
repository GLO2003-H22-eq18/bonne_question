package ulaval.glo2003.seller.exceptions;

import ulaval.glo2003.exceptions.ItemNotFoundException;

public class SellerNotFoundException extends ItemNotFoundException {
    public SellerNotFoundException() {
        super("Seller id could not be found");
    }
}
