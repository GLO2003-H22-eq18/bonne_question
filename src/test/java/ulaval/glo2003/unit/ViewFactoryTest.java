package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.domain.ViewFactory;
import ulaval.glo2003.subjects.OffsetDateTimeSubject;

public class ViewFactoryTest {

    private static ViewFactory viewFactory;

    @BeforeAll
    static void setUp() {
        viewFactory = new ViewFactory();
    }

    @Test
    void whenCreatingView_thenCreatesValidView() {
        View view = viewFactory.create();

        assertThat(view.getId()).isNotNull();
        OffsetDateTimeSubject.assertThat(view.getCreatedAt().toString()).isWithinExpectedRange();
    }

    @Test
    void whenCreatingTwoViews_thenCreatesValidViewsWithDifferentId() {
        View view1 = viewFactory.create();
        View view2 = viewFactory.create();

        assertThat(view1.getId()).isNotEqualTo(view2.getId());
    }
}
