package ulaval.glo2003.Exceptions;

public class InvalidSellerBioException extends InvalidArgumentException {
    public InvalidSellerBioException() {
        super("Seller bio is invalid");
    }
}
