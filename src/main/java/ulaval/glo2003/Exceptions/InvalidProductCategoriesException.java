package ulaval.glo2003.Exceptions;

public class InvalidProductCategoriesException extends InvalidArgumentException{
    public InvalidProductCategoriesException(){
        super("Product categories is invalid");
    }
}

