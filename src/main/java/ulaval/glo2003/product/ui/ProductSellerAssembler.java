package ulaval.glo2003.product.ui;

public class ProductSellerAssembler {

    public static ProductSellerResponse createProductSellerResponse(String sellerId,
                                                                    String sellerName) {
        return new ProductSellerResponse(sellerId, sellerName);
    }
}
