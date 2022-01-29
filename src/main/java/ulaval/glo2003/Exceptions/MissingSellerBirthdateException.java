package ulaval.glo2003.Exceptions;

public class MissingSellerBirthdateException extends MissingArgumentException{
    public MissingSellerBirthdateException() {
        super("Seller birthdate missing");
    }
}
