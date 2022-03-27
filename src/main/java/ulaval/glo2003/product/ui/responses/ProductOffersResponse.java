package ulaval.glo2003.product.ui.responses;

public class ProductOffersResponse {
    public Integer count;
    public Double mean;

    public ProductOffersResponse() {
        super();
    }

    public ProductOffersResponse(Double mean, Integer count) {
        this.count = count;
        this.mean = mean;
    }
}
