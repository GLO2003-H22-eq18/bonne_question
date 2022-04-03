package ulaval.glo2003.unit.Assembler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.DetailedProductOffersAssembler;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;

public class DetailedProductOffersAssemblerTest {

    private static final Double AMOUNT1 = 25.00;
    private static final Double AMOUNT2 = 75.00;
    private static DetailedProductOffersAssembler detailedProductOffersAssembler;

    @BeforeAll
    public static void setup() {
        detailedProductOffersAssembler = new DetailedProductOffersAssembler();
    }

    @Test
    void givenOneOffer_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse() {
        Offer offer = getOffer(AMOUNT1);

        List<Offer> listOffer = new ArrayList();
        listOffer.add(offer);

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.max).isEqualTo(AMOUNT1);
        assertThat(detailedProductOffersResponse.min).isEqualTo(AMOUNT1);
        assertThat(detailedProductOffersResponse.mean).isEqualTo(AMOUNT1);
    }

    @Test
    void givenNoOffer_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse() {
        List<Offer> listOffer = new ArrayList();

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.max).isEqualTo(null);
        assertThat(detailedProductOffersResponse.min).isEqualTo(null);
        assertThat(detailedProductOffersResponse.mean).isEqualTo(null);
    }

    @Test
    void givenTwoOffers_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse() {
        Offer offer1 = getOffer(AMOUNT1);
        Offer offer2 = getOffer(AMOUNT2);

        List<Offer> listOffer = new ArrayList();
        listOffer.add(offer1);
        listOffer.add(offer2);

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(listOffer.size());
        assertThat(detailedProductOffersResponse.max).isEqualTo(AMOUNT2);
        assertThat(detailedProductOffersResponse.min).isEqualTo(AMOUNT1);
        assertThat(detailedProductOffersResponse.mean).isEqualTo((AMOUNT1 + AMOUNT2) / 2);
    }
}
