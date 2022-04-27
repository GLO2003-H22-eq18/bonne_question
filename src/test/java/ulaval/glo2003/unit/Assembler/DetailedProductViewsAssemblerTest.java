package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getView;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.assemblers.DetailedProductViewsAssembler;
import ulaval.glo2003.product.ui.responses.DetailedProductViewsResponse;

public class DetailedProductViewsAssemblerTest {

    private static DetailedProductViewsAssembler detailedProductViewsAssembler;

    @BeforeAll
    public static void setup() {
        detailedProductViewsAssembler = new DetailedProductViewsAssembler();
    }

    @Test
    void givenOneView_whenCreateDetailedProductViewsResponse_thenCorrectDetailedProductViewsResponse() {
        View view = getView();

        List<View> views = new ArrayList();
        views.add(view);

        DetailedProductViewsResponse detailedProductViewsResponse =
                detailedProductViewsAssembler.createDetailedProductViewsResponse(views);

        assertThat(detailedProductViewsResponse.count).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.items.size()).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.mostRecentView).isEqualTo(
                views.get(views.size() - 1).getCreatedAt().toString());
    }

    @Test
    void givenNoView_whenCreateDetailedProductViewsResponse_thenCorrectDetailedProductViewsResponse() {
        List<View> views = new ArrayList();

        DetailedProductViewsResponse detailedProductViewsResponse =
                detailedProductViewsAssembler.createDetailedProductViewsResponse(views);

        assertThat(detailedProductViewsResponse.count).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.items.size()).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.mostRecentView).isNull();
    }

    @Test
    void givenTwoViews_whenCreateDetailedProductViewsResponse_thenCorrectDetailedProductViewsResponse() {
        View view1 = getView();
        View view2 = getView();

        List<View> views = new ArrayList();
        views.add(view1);
        views.add(view2);

        DetailedProductViewsResponse detailedProductViewsResponse =
                detailedProductViewsAssembler.createDetailedProductViewsResponse(views);

        assertThat(detailedProductViewsResponse.count).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.items.size()).isEqualTo(views.size());
        assertThat(detailedProductViewsResponse.mostRecentView).isEqualTo(
                views.get(views.size() - 1).getCreatedAt().toString());
    }
}
