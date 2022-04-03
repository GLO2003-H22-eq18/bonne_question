package ulaval.glo2003.unit;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.OfferAssembler;
import ulaval.glo2003.product.ui.responses.OfferResponse;

import static com.google.common.truth.Truth.assertThat;

class OfferAssemblerTest {

    private static OfferAssembler offerAssembler;

    @BeforeAll
    public static void setup() { offerAssembler = new OfferAssembler();}

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

    public Offer getOffer() {
        Double amount = 25.0;
        String message = "Significant message";
        ObjectId id = new ObjectId();
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "5989782222";

        return new Offer(id, amount, message, name ,email, phoneNumber);
    }
}
