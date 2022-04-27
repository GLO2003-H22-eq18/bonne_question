package ulaval.glo2003.product.infrastructure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("views")
public class ViewModel {
    @Id
    private ObjectId id;

    public ViewModel() {}

    public ViewModel(
            ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
}