package ulaval.glo2003.Product.Domain;

import java.util.List;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Product.UI.ProductUtil;
import ulaval.glo2003.Seller.Domain.Seller;

public class ProductFactory {
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
                productSeller.getName());
    }
}
