package ulaval.glo2003.Exceptions;

public class InvalidSellerBirthdateException extends InvalidArgumentException {
    public InvalidSellerBirthdateException() {
        super("Seller birthdate is invalid");
    }
}
