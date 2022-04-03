package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.ProductOffersAssembler;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;

public class ProductOffersAssemblerTest {

    private static final Double AMOUNT1 = 25.00;
    private static final Double AMOUNT2 = 75.00;
    private static ProductOffersAssembler productOffersAssembler;

    @BeforeAll
    public static void setup() {
        productOffersAssembler = new ProductOffersAssembler();
    }

    @Test
    void givenZeroOffers_whenCreateProductOffersResponse_thenCorrectProductOffersResponse() {
        List<Offer> offers = new ArrayList<>();

        ProductOffersResponse productOffersResponse =
                productOffersAssembler.createProductOffersResponse(offers);

        assertThat(productOffersResponse.count).isEqualTo(offers.size());
        assertThat(productOffersResponse.mean).isEqualTo(null);
    }

    @Test
    void givenOneOffers_whenCreateProductOffersResponse_thenCorrectProductOffersResponse() {
        Offer offer = getOffer(AMOUNT1);
        List<Offer> offers = new ArrayList<>();
        offers.add(offer);

        ProductOffersResponse productOffersResponse =
                productOffersAssembler.createProductOffersResponse(offers);

        assertThat(productOffersResponse.count).isEqualTo(offers.size());
        assertThat(productOffersResponse.mean).isEqualTo(AMOUNT1);
    }

    @Test
    void givenMultipleOffers_whenCreateProductOffersResponse_thenCorrectProductOffersResponse() {
        Offer offer1 = getOffer(AMOUNT1);
        Offer offer2 = getOffer(AMOUNT2);
        List<Offer> offers = new ArrayList<>();
        offers.add(offer1);
        offers.add(offer2);

        ProductOffersResponse productOffersResponse =
                productOffersAssembler.createProductOffersResponse(offers);

        assertThat(productOffersResponse.count).isEqualTo(offers.size());
        assertThat(productOffersResponse.mean).isEqualTo((AMOUNT1 + AMOUNT2) / 2);
    }
}
