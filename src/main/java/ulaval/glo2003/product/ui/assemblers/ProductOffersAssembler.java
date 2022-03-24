package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.ui.responses.ProductOffersResponse;

public class ProductOffersAssembler {

    public static ProductOffersResponse createProductOffersResponse(Integer count) {
        return new ProductOffersResponse(count);
    }
}
