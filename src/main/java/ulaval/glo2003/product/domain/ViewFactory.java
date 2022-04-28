package ulaval.glo2003.product.domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import org.bson.types.ObjectId;

public class ViewFactory {
    public View create() {
        return new View(
                new ObjectId(),
                OffsetDateTime.now(Clock.systemUTC())
        );
    }
}
