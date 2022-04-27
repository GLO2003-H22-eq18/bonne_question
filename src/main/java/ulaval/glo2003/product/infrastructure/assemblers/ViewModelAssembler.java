package ulaval.glo2003.product.infrastructure.assemblers;

import java.time.OffsetDateTime;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.infrastructure.models.ViewModel;

public class ViewModelAssembler {
    public ViewModel createViewModel(View view) {
        ObjectId id = view.getId();
        String createdAt = view.getCreatedAt().toString();

        return new ViewModel(id, createdAt);
    }

    public View createView(ViewModel viewModel) {
        ObjectId id = viewModel.getId();
        OffsetDateTime createdAt = OffsetDateTime.parse(viewModel.getCreatedAt());
        return new View(id, createdAt);
    }
}
