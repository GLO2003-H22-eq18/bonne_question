package ulaval.glo2003.Exceptions;

public class SellerNotFoundException extends ItemNotFoundException {
    public SellerNotFoundException() {
        super("Seller id could not be found");
    }
}
