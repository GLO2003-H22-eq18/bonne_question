package ulaval.glo2003.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.OfferAssembler;
import ulaval.glo2003.product.ui.responses.OfferResponse;

import java.time.OffsetDateTime;

import static com.google.common.truth.Truth.assertThat;

class OfferAssemblerTest {

    private static OfferAssembler offerAssembler;

   /* private static final Double AMOUNT = 25.0;
    private static final String MESSAGE = "Significant message";
    private static final Double INVALID_AMOUNT = null;
    private static final String INVALID_MESSAGE = "  \n\t";*/

    @BeforeAll
    public static void setup() { offerAssembler = new OfferAssembler();}

    public Offer getOffer() {
        Double amount = 25.0;
        String message = "Significant message";
        String id = "1";
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "5989782222";

        return new Offer(id, amount, message, name ,email, phoneNumber);
    }

    @Test
    void givenOffer_whenCreateOfferResponse_thenCorrectOfferResponse() {
        Offer offer = getOffer();

        OfferResponse offerResponse = offerAssembler.createOfferResponse(offer);

        assertThat(offerResponse.id).isEqualTo(offer.getId());
        assertThat(offerResponse.amount).isEqualTo(offer.getAmount());
        assertThat(offerResponse.message).isEqualTo(offer.getMessage());
        assertThat(offerResponse.buyer.name).isEqualTo(offer.getName());
        assertThat(offerResponse.buyer.email).isEqualTo(offer.getEmail());
        assertThat(offerResponse.buyer.phoneNumber).isEqualTo(offer.getPhoneNumber());
    }
}
