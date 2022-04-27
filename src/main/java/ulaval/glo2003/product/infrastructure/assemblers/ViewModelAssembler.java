package ulaval.glo2003.product.infrastructure.assemblers;

import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.infrastructure.models.ViewModel;

public class ViewModelAssembler {
    public ViewModel createViewModel(View view) {
        ObjectId id = view.getId();

        return new ViewModel(id);
    }

    public View createView(ViewModel viewModel) {
        ObjectId id = viewModel.getId();

        return new View(id);
    }
}
