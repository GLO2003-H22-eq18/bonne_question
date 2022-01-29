package ulaval.glo2003.Exceptions;

public class InvalidSellerNameException extends InvalidArgumentException{
    public InvalidSellerNameException(){
        super("Seller name invalid");
    }
}
