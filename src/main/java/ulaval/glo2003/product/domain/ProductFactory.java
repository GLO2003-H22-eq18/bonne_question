package ulaval.glo2003.product.domain;

import java.util.List;
import ulaval.glo2003.product.ui.ProductRequest;
import ulaval.glo2003.product.ui.ProductUtil;
import ulaval.glo2003.seller.domain.Seller;

public class ProductFactory {
    private static int currentId = 0;

    public Product create(Seller productSeller, ProductRequest productRequest) {
        ProductUtil.checkMissingParam(productRequest);
        ProductUtil.checkNewProductInvalidParam(productRequest);

        List<ProductCategory> categories =
                ProductUtil.createProductCategoryList(productRequest.categories);

        return new Product(
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                categories,
                productSeller.getId(),
                productSeller.getName(),
                currentId++);
    }
}
