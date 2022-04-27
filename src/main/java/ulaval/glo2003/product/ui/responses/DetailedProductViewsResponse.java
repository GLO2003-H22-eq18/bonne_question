package ulaval.glo2003.product.ui.responses;

import java.util.List;

public class DetailedProductViewsResponse {
    public Integer count;
    public List<ViewResponse> items;

    public DetailedProductViewsResponse() {
        super();
    }

    public DetailedProductViewsResponse(Integer count, List<ViewResponse> items) {
        this.count = count;
        this.items = items;
    }
}
