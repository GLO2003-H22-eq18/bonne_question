package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.responses.ViewResponse;

public class ViewAssembler {

    public ViewAssembler() {
    }

    public ViewResponse createViewResponse(View view) {
        String id = view.getId().toString();
        String createdAt = view.getCreatedAt().toString();

        return new ViewResponse(id, createdAt);
    }
}
