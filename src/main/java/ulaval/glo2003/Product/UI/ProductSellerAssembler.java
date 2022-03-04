package ulaval.glo2003.Product.UI;

public class ProductSellerAssembler {

    public static ProductSellerResponse createProductSellerResponse(String sellerId,
                                                                    String sellerName) {
        return new ProductSellerResponse(sellerId, sellerName);
    }
}
