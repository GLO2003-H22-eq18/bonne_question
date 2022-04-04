package ulaval.glo2003.product.ui.requests;

import java.util.List;
import ulaval.glo2003.product.domain.ProductCategory;

public class ProductRequest {
    public String title;
    public String description;
    public Double suggestedPrice;
    public List<String> categories;
}
