package ulaval.glo2003.product.domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import org.bson.types.ObjectId;

public class View {
    private final ObjectId id;
    private final OffsetDateTime createdAt;

    public View(
            ObjectId id) {
        this.id = id;

        this.createdAt = OffsetDateTime.now(Clock.systemUTC());
    }

    public ObjectId getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
