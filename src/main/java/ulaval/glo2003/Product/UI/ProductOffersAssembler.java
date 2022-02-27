package ulaval.glo2003.Product.UI;

import ulaval.glo2003.Product.Domain.Product;

public class ProductOffersAssembler {

    public static ProductOffersResponse createProductOffersResponse(Integer count){
        return new ProductOffersResponse(count);
    }
}
