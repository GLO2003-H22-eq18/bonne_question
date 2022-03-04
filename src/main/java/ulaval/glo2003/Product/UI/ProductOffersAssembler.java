package ulaval.glo2003.Product.UI;

public class ProductOffersAssembler {

    public static ProductOffersResponse createProductOffersResponse(Integer count){
        return new ProductOffersResponse(count);
    }
}
