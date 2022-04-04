package ulaval.glo2003.product.ui.responses;

public class BuyerResponse {
    public String name;
    public String email;
    public String phoneNumber;

    public BuyerResponse() {
        super();
    }

    public BuyerResponse(String name,
                         String email,
                         String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
