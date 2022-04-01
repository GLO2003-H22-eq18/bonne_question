package ulaval.glo2003.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.OfferAssembler;
import ulaval.glo2003.product.ui.responses.BuyerResponse;

import static com.google.common.truth.Truth.assertThat;

public class BuyerAssemblerTest {

    private static OfferAssembler offerAssembler;

    @BeforeAll
    public static void setup() { offerAssembler = new OfferAssembler();}

    public Offer getOffer() {
        Double amount = 25.0;
        String message = "Significant message";
        String id = "1";
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "5989782222";

        return new Offer(id, amount, message, name ,email ,phoneNumber);
    }

    @Test
    void givenOffer_whenCreateOfferResponse_thenCorrectOfferResponse() {
        Offer offer = getOffer();

        BuyerResponse buyerResponse = new BuyerResponse(
                "John",
                "sickmail@hotmail.com",
                "5989782222");

        assertThat(buyerResponse.name).isEqualTo(offer.getName());
        assertThat(buyerResponse.email).isEqualTo(offer.getEmail());
        assertThat(buyerResponse.phoneNumber).isEqualTo(offer.getPhoneNumber());
    }
}
