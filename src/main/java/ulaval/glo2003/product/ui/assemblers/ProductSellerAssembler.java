package ulaval.glo2003.product.ui.assemblers;

import org.bson.types.ObjectId;
import ulaval.glo2003.product.ui.responses.ProductSellerResponse;

public class ProductSellerAssembler {

    public ProductSellerResponse createProductSellerResponse(ObjectId sellerId,
                                                             String sellerName) {
        return new ProductSellerResponse(sellerId.toString(), sellerName);
    }
}
