package ulaval.glo2003.product.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("views")
public class ViewModel {
    @Id
    private ObjectId id;
    private String createdAt;

    public ViewModel() {
    }

    public ViewModel(
            ObjectId id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
