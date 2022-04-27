package ulaval.glo2003.product.ui.responses;

public class OfferResponse {
    public String id;
    public String createdAt;
    public Double amount;
    public String message;
    public BuyerResponse buyer;

    public OfferResponse() {
        super();
    }

    public OfferResponse(String id,
                         String createdAt,
                         Double amount,
                         String message,
                         BuyerResponse buyer) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.message = message;
        this.buyer = buyer;
    }
}
