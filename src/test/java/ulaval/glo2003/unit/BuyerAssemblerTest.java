package ulaval.glo2003.unit;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.BuyerAssembler;
import ulaval.glo2003.product.ui.responses.BuyerResponse;

import static com.google.common.truth.Truth.assertThat;

public class BuyerAssemblerTest {

    private static BuyerAssembler buyerAssembler;

    @BeforeAll
    public static void setup() { buyerAssembler = new BuyerAssembler();}

    @Test
    void givenOffer_whenCreateOfferResponse_thenCorrectOfferResponse() {
        Offer offer = getOffer();

        BuyerResponse buyerResponse = buyerAssembler.createBuyerResponse(offer);

        assertThat(buyerResponse.name).isEqualTo(offer.getName());
        assertThat(buyerResponse.email).isEqualTo(offer.getEmail());
        assertThat(buyerResponse.phoneNumber).isEqualTo(offer.getPhoneNumber());
    }

    public Offer getOffer() {
        Double amount = 25.0;
        String message = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
        ObjectId id = new ObjectId();
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "5989782222";

        return new Offer(id, amount, message, name ,email ,phoneNumber);
    }
}
