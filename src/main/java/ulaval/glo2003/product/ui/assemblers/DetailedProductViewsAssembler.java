package ulaval.glo2003.product.ui.assemblers;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.responses.DetailedProductViewsResponse;
import ulaval.glo2003.product.ui.responses.ViewResponse;

public class DetailedProductViewsAssembler {

    private final ViewAssembler viewAssembler;

    public DetailedProductViewsAssembler() {
        viewAssembler = new ViewAssembler();
    }

    public DetailedProductViewsResponse createDetailedProductViewsResponse(
            List<View> views) {
        String mostRecentVisit = getMostRecentView(views);
        Integer count = getViewsCount(views);

        List<ViewResponse> viewResponses = views.stream()
                .map(viewAssembler::createViewResponse)
                .collect(Collectors.toList());

        return new DetailedProductViewsResponse(mostRecentVisit, count, viewResponses);
    }

    private String getMostRecentView(List<View> views) {
        return views.isEmpty() ? null : views.get(views.size() - 1).getCreatedAt().toString();
    }

    private Integer getViewsCount(List<View> views) {
        return views.size();
    }
}
