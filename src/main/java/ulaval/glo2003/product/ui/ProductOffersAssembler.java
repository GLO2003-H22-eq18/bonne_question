package ulaval.glo2003.product.ui;

public class ProductOffersAssembler {

    public static ProductOffersResponse createProductOffersResponse(Integer count) {
        return new ProductOffersResponse(count);
    }
}
