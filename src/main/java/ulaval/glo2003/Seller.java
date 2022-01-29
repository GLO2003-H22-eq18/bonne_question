package ulaval.glo2003;

import java.time.OffsetDateTime;

public class Seller {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;

    public Seller(String id, OffsetDateTime createdAt, String name, String bio){
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
    }
}
