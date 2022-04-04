package ulaval.glo2003.unit.Assembler;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.exceptions.offers.MissingOfferAmountException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferEmailException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferMessageException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferNameException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferPhoneNumberException;
import ulaval.glo2003.product.ui.assemblers.OfferRequestAssembler;
import ulaval.glo2003.product.ui.requests.OfferRequest;

public class OfferRequestAssemblerTest {

    private static final String NAME = "John Doe";
    private static final String EMAIL = "john.doe@email.com";
    private static final String PHONE_NUMBER = "18191234567";
    private static final Double AMOUNT = 48.23;
    private static final String MESSAGE =
            "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
    private static OfferRequestAssembler offerRequestAssembler;

    @BeforeAll
    public static void setUp() {
        offerRequestAssembler = new OfferRequestAssembler();
    }

    @Test
    void
    givenOfferRequestWithMissingName_whenCreatingOffer_thenMissingOfferNameException() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.email = EMAIL;
        offerRequest.phoneNumber = PHONE_NUMBER;
        offerRequest.amount = AMOUNT;
        offerRequest.message = MESSAGE;

        assertThrows(
                MissingOfferNameException.class,
                () -> offerRequestAssembler.checkOfferRequestMissingParams(offerRequest));
    }

    @Test
    void
    givenOfferRequestWithMissingEmail_whenCreatingOffer_thenMissingOfferEmailException() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = NAME;
        offerRequest.phoneNumber = PHONE_NUMBER;
        offerRequest.amount = AMOUNT;
        offerRequest.message = MESSAGE;

        assertThrows(
                MissingOfferEmailException.class,
                () -> offerRequestAssembler.checkOfferRequestMissingParams(offerRequest));
    }

    @Test
    void
    givenOfferRequestWithMissingPhoneNumber_whenCreatingOffer_thenMissingOfferPhoneNumberException() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = NAME;
        offerRequest.email = EMAIL;
        offerRequest.amount = AMOUNT;
        offerRequest.message = MESSAGE;

        assertThrows(
                MissingOfferPhoneNumberException.class,
                () -> offerRequestAssembler.checkOfferRequestMissingParams(offerRequest));
    }

    @Test
    void
    givenOfferRequestWithMissingAmount_whenCreatingOffer_thenMissingOfferAmountException() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = NAME;
        offerRequest.email = EMAIL;
        offerRequest.phoneNumber = PHONE_NUMBER;
        offerRequest.message = MESSAGE;

        assertThrows(
                MissingOfferAmountException.class,
                () -> offerRequestAssembler.checkOfferRequestMissingParams(offerRequest));
    }

    @Test
    void
    givenOfferRequestWithMissingMessage_whenCreatingOffer_thenMissingOfferMessageException() {
        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = NAME;
        offerRequest.email = EMAIL;
        offerRequest.phoneNumber = PHONE_NUMBER;
        offerRequest.amount = AMOUNT;

        assertThrows(
                MissingOfferMessageException.class,
                () -> offerRequestAssembler.checkOfferRequestMissingParams(offerRequest));
    }
}
