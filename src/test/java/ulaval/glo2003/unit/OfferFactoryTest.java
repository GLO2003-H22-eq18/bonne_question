package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferFactory;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferAmountException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferEmailException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferMessageException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferNameException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferPhoneNumberException;
import ulaval.glo2003.product.ui.requests.OfferRequest;

public class OfferFactoryTest {

    private static final String NAME = "John Doe";
    private static final String INVALID_NAME = "";
    private static final String MESSAGE =
            "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
    private static final String INVALID_MESSAGE = "Message pas assez long";
    private static final String EMAIL = "john.doe@email.com";
    private static final String INVALID_EMAIL = "john.doeemail.com";
    private static final String PHONENUMBER = "18191234567";
    private static final String INVALID_PHONENUMBER = "15a26d257qw";
    private static final Double PRODUCT_SUGGESTED_PRICE = 100.0;
    private static final Double AMOUNT = 120.0;
    private static final Double INVALID_AMOUNT = 90.0;

    private static OfferFactory offerFactory;

    @BeforeAll
    static void setUp() {
        offerFactory = new OfferFactory();
    }

    public OfferRequest createOfferRequest(
            String name, String message, String email, String phoneNumber, Double amount) {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = name;
        offerRequest.email = email;
        offerRequest.message = message;
        offerRequest.phoneNumber = phoneNumber;
        offerRequest.amount = amount;

        return offerRequest;
    }

    @Test
    void givenOfferRequest_whenCreatingOffer_thenCorrectOffer() {
        OfferRequest offerRequest =
                createOfferRequest(NAME, MESSAGE, EMAIL, PHONENUMBER, AMOUNT);

        Offer offer = offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest);

        assertThat(offer.getName()).isEqualTo(NAME);
        assertThat(offer.getEmail()).isEqualTo(EMAIL);
        assertThat(offer.getMessage()).isEqualTo(MESSAGE);
        assertThat(offer.getPhoneNumber()).isEqualTo(PHONENUMBER);
        assertThat(offer.getAmount()).isEqualTo(AMOUNT);
    }

    @Test
    void givenOfferRequestWithInvalidName_whenCreatingOffer_thenInvalidOfferNameException() {
        OfferRequest offerRequest =
                createOfferRequest(INVALID_NAME, MESSAGE, EMAIL, PHONENUMBER, AMOUNT);

        assertThrows(
                InvalidOfferNameException.class,
                () -> offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest)
        );
    }

    @Test
    void givenOfferRequestWithInvalidMessage_whenCreatingOffer_thenInvalidOfferMessageException() {
        OfferRequest offerRequest =
                createOfferRequest(NAME, INVALID_MESSAGE, EMAIL, PHONENUMBER, AMOUNT);

        assertThrows(
                InvalidOfferMessageException.class,
                () -> offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest)
        );
    }

    @Test
    void givenOfferRequestWithInvalidEmail_whenCreatingOffer_thenInvalidOfferEmailException() {
        OfferRequest offerRequest =
                createOfferRequest(NAME, MESSAGE, INVALID_EMAIL, PHONENUMBER, AMOUNT);

        assertThrows(
                InvalidOfferEmailException.class,
                () -> offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest)
        );
    }

    @Test
    void givenOfferRequestWithInvalidPhoneNumber_whenCreatingOffer_thenInvalidOfferPhoneNumberException() {
        OfferRequest offerRequest =
                createOfferRequest(NAME, MESSAGE, EMAIL, INVALID_PHONENUMBER, AMOUNT);

        assertThrows(
                InvalidOfferPhoneNumberException.class,
                () -> offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest)
        );
    }

    @Test
    void givenOfferRequestWithInvalidAmount_whenCreatingOffer_thenInvalidOfferAmountException() {
        OfferRequest offerRequest =
                createOfferRequest(NAME, MESSAGE, EMAIL, PHONENUMBER, INVALID_AMOUNT);

        assertThrows(
                InvalidOfferAmountException.class,
                () -> offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest)
        );
    }

    @Test
    void givenTwoOfferRequest_whenCreatingOffer_thenCorrectOfferWithDifferentId() {
        OfferRequest offerRequest1 =
                createOfferRequest(NAME, MESSAGE, EMAIL, PHONENUMBER, AMOUNT);
        OfferRequest offerRequest2 =
                createOfferRequest(NAME, MESSAGE, EMAIL, PHONENUMBER, PRODUCT_SUGGESTED_PRICE);

        Offer offer1 = offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest1);
        Offer offer2 = offerFactory.create(PRODUCT_SUGGESTED_PRICE, offerRequest2);

        assertThat(offer1.getId()).isNotEqualTo(offer2.getId());
    }
}
