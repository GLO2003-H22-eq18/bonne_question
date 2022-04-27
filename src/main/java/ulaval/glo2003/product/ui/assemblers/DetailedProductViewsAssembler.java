package ulaval.glo2003.product.ui.assemblers;

import static java.lang.Double.MAX_VALUE;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.product.ui.responses.DetailedProductViewsResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;
import ulaval.glo2003.product.ui.responses.ViewResponse;

public class DetailedProductViewsAssembler {

    private final ViewAssembler viewAssembler;

    public DetailedProductViewsAssembler() {
        viewAssembler = new ViewAssembler();
    }

    public DetailedProductViewsResponse createDetailedProductViewsResponse(
            List<View> views) {
        Integer count = getViewsCount(views);

        List<ViewResponse> viewResponses = views.stream()
                .map(viewAssembler::createViewResponse)
                .collect(Collectors.toList());

        return new DetailedProductViewsResponse(count, viewResponses);
    }


    private Integer getViewsCount(List<View> views) {
        return views.size();
    }
}
