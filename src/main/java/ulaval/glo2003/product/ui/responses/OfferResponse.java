package ulaval.glo2003.product.ui.responses;

import org.bson.types.ObjectId;

public class OfferResponse {
    public ObjectId id;
    public String createdAt;
    public Double amount;
    public String message;
    public BuyerResponse buyer;

    public OfferResponse(ObjectId id,
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
