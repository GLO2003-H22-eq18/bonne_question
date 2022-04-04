package ulaval.glo2003.unit.Assembler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.OfferAssembler;
import ulaval.glo2003.product.ui.responses.BuyerResponse;
import ulaval.glo2003.product.ui.responses.OfferResponse;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;

class OfferAssemblerTest {

    private static OfferAssembler offerAssembler;

    @BeforeAll
    public static void setup() {
        offerAssembler = new OfferAssembler();
    }

    @Test
    void givenOffer_whenCreateOfferResponse_thenCorrectOfferResponse() {
        Offer offer = getOffer(25.03);

        OfferResponse offerResponse = offerAssembler.createOfferResponse(offer);

        assertThat(offerResponse.id).isEqualTo(offer.getId().toString());
        assertThat(offerResponse.amount).isEqualTo(offer.getAmount());
        assertThat(offerResponse.message).isEqualTo(offer.getMessage());
        assertThat(offerResponse.createdAt).isEqualTo(offer.getCreatedAt().toString());
        assertThat(offerResponse.buyer).isInstanceOf(BuyerResponse.class);
    }
}
