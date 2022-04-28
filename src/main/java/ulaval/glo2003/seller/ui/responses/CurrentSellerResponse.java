package ulaval.glo2003.seller.ui.responses;

import java.util.List;

public class CurrentSellerResponse {
    public String id;
    public String name;
    public String createdAt;
    public String bio;
    public String birthDate;
    public List<CurrentSellerProductResponse> products;

    public CurrentSellerResponse() {
        super();
    }

    public CurrentSellerResponse(String id,
                                 String name,
                                 String createdAt,
                                 String bio,
                                 String birthDate,
                                 List<CurrentSellerProductResponse> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.products = products;
    }
}
