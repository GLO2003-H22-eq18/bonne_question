package ulaval.glo2003.unit.Assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.BuyerAssembler;
import ulaval.glo2003.product.ui.responses.BuyerResponse;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;

public class BuyerAssemblerTest {

    private static BuyerAssembler buyerAssembler;

    @BeforeAll
    public static void setup() {
        buyerAssembler = new BuyerAssembler();
    }

    @Test
    void givenOffer_whenCreateOfferResponse_thenCorrectOfferResponse() {
        Offer offer = getOffer(25.30);

        BuyerResponse buyerResponse = buyerAssembler.createBuyerResponse(offer);

        assertThat(buyerResponse.name).isEqualTo(offer.getName());
        assertThat(buyerResponse.email).isEqualTo(offer.getEmail());
        assertThat(buyerResponse.phoneNumber).isEqualTo(offer.getPhoneNumber());
    }
}
