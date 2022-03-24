package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.ui.responses.ProductSellerResponse;

public class ProductSellerAssembler {

    public static ProductSellerResponse createProductSellerResponse(String sellerId,
                                                                    String sellerName) {
        return new ProductSellerResponse(sellerId, sellerName);
    }
}
