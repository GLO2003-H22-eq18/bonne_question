package ulaval.glo2003.product.ui.requests;

import java.util.List;
import ulaval.glo2003.product.domain.ProductCategory;

public class FilteredProductRequest {
    public String sellerId;
    public String title;
    public Double minPrice;
    public Double maxPrice;
    public List<ProductCategory> categories;
}
