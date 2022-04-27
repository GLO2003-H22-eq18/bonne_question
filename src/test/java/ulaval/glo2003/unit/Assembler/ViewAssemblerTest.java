package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getView;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.assemblers.ViewAssembler;
import ulaval.glo2003.product.ui.responses.ViewResponse;

public class ViewAssemblerTest {

    private static ViewAssembler viewAssembler;

    @BeforeAll
    public static void setup() {
        viewAssembler = new ViewAssembler();
    }

    @Test
    void givenView_whenCreateViewResponse_thenCorrectViewResponse() {
        View view = getView();

        ViewResponse viewResponse = viewAssembler.createViewResponse(view);

        assertThat(viewResponse.id).isEqualTo(view.getId().toString());
        assertThat(viewResponse.createdAt).isEqualTo(view.getCreatedAt().toString());
    }
}
