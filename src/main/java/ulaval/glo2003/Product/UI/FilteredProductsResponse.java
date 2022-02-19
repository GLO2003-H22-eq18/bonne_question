package ulaval.glo2003.Product.UI;

import ulaval.glo2003.Product.Domain.Product;

import java.util.List;

public class FilteredProductsResponse {
    public List<ProductResponse> products;
    
    public FilteredProductsResponse(List<ProductResponse> products) {
        this.products = products;
    }
}
