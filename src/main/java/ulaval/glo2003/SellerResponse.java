package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.List;

public class SellerResponse {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;
    public List<String> products;

    public SellerResponse(String id, OffsetDateTime createdAt, String name, String bio, List<String> products) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.products = products;
    }
}
