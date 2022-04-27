package ulaval.glo2003.product.domain;

import java.time.OffsetDateTime;
import org.bson.types.ObjectId;

public class View {
    private final ObjectId id;
    private final OffsetDateTime createdAt;

    public View(
            ObjectId id,
            OffsetDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
