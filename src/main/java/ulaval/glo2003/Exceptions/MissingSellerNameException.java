package ulaval.glo2003.Exceptions;

public class MissingSellerNameException extends MissingArgumentException{
    public MissingSellerNameException() {
        super("Seller name missing");
    }
}
