package ulaval.glo2003.unit;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.ui.assemblers.DetailedProductOffersAssembler;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;

import javax.print.attribute.standard.MediaSize;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class DetailedProductOffersAssemblerTest {

    private static final ObjectId ID = new ObjectId();
    private static final Double AMOUNT = 50.0;
    private static final Double AMOUNT2 = 100.0;
    private static final String MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
    private static final String NAME = "John";
    private static final String EMAIL = "bigboy@email.com";
    private static final String PHONE_NUMBER = "10978765555";

    private static DetailedProductOffersAssembler detailedProductOffersAssembler;

    @BeforeAll
    public static void setup() {
        detailedProductOffersAssembler = new DetailedProductOffersAssembler();
    }

    public Offer getOffer(Double amount) {
        return new Offer(ID, amount, MESSAGE, NAME,  EMAIL, PHONE_NUMBER);
    }

    @Test
    void givenOneOffer_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse(){
        Offer offer = getOffer(AMOUNT);

       List<Offer> listOffer = new ArrayList();
       listOffer.add(offer);

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(1);
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(1);
        assertThat(detailedProductOffersResponse.max).isEqualTo(AMOUNT);
        assertThat(detailedProductOffersResponse.min).isEqualTo(AMOUNT);
        assertThat(detailedProductOffersResponse.mean).isEqualTo(AMOUNT);
    }

    @Test
    void givenNoOffer_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse(){

        List<Offer> listOffer = new ArrayList();

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(0);
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(0);
        assertThat(detailedProductOffersResponse.max).isEqualTo(null);
        assertThat(detailedProductOffersResponse.min).isEqualTo(null);
        assertThat(detailedProductOffersResponse.mean).isEqualTo(null);
    }

    @Test
    void givenTwoOffers_whenCreateDetailedProductOfferResponse_thenCorrectDetailedProductOfferResponse(){
        Offer offer1 = getOffer(AMOUNT);
        Offer offer2 = getOffer(AMOUNT2);

        List<Offer> listOffer = new ArrayList();
        listOffer.add(offer1);
        listOffer.add(offer2);

        DetailedProductOffersResponse detailedProductOffersResponse =
                detailedProductOffersAssembler.createDetailedProductOffersResponse(listOffer);

        assertThat(detailedProductOffersResponse.count).isEqualTo(2);
        assertThat(detailedProductOffersResponse.items.size()).isEqualTo(2);
        assertThat(detailedProductOffersResponse.max).isEqualTo(AMOUNT2);
        assertThat(detailedProductOffersResponse.min).isEqualTo(AMOUNT);
        assertThat(detailedProductOffersResponse.mean).isEqualTo(75.0);
    }
}
