package ulaval.glo2003.Exceptions;

public class InvalidSellerIdException extends InvalidArgumentException {
    public InvalidSellerIdException() {
        super("Seller id is already used");
    }
}
