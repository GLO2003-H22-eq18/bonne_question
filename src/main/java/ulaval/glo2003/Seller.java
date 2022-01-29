package ulaval.glo2003;

import java.time.OffsetDateTime;
import java.util.Date;

public class Seller {
    public String id;
    public OffsetDateTime createdAt;
    public String name;
    public String bio;
    public OffsetDateTime birthDate;

    public Seller(String id, OffsetDateTime createdAt, String name, String bio,
                  OffsetDateTime birthDate){
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
    }
}
