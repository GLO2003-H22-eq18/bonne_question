package ulaval.glo2003.Exceptions;

public class MissingSellerBioException extends MissingArgumentException{
    public MissingSellerBioException() {
        super("Seller bio missing");
    }
}
