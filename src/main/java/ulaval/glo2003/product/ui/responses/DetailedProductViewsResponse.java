package ulaval.glo2003.product.ui.responses;

import java.util.List;

public class DetailedProductViewsResponse {
    public String mostRecentView;
    public Integer count;
    public List<ViewResponse> items;

    public DetailedProductViewsResponse() {
        super();
    }

    public DetailedProductViewsResponse(String mostRecentView, Integer count,
                                        List<ViewResponse> items) {
        this.mostRecentView = mostRecentView;
        this.count = count;
        this.items = items;
    }
}
