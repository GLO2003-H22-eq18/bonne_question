package ulaval.glo2003.product.ui.responses;

import java.util.List;

public class DetailedProductOffersResponse {

    public Double min;
    public Double max;
    public Double mean;
    public Integer count;
    public List<OfferResponse> items;

    public DetailedProductOffersResponse() {
        super();
    }

    public DetailedProductOffersResponse(Double min,
                                         Double max,
                                         Double mean,
                                         Integer count,
                                         List<OfferResponse> items) {
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.count = count;
        this.items = items;
    }
}
