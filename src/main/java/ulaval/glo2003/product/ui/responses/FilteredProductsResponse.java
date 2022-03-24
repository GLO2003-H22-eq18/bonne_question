package ulaval.glo2003.product.ui.responses;

import java.util.List;

public class FilteredProductsResponse {
    public List<ProductResponse> products;

    public FilteredProductsResponse() {
        super();
    }

    public FilteredProductsResponse(List<ProductResponse> products) {
        this.products = products;
    }
}
