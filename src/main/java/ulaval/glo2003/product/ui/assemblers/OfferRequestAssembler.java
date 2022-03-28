package ulaval.glo2003.product.ui.assemblers;

import ulaval.glo2003.product.exceptions.offers.MissingOfferAmountException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferEmailException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferMessageException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferNameException;
import ulaval.glo2003.product.exceptions.offers.MissingOfferPhoneNumberException;
import ulaval.glo2003.product.ui.requests.OfferRequest;

public class OfferRequestAssembler {

    public void checkOfferRequestMissingParams(OfferRequest offerRequest) {
        checkMissingOfferName(offerRequest.name);
        checkMissingOfferEmail(offerRequest.email);
        checkMissingOfferPhoneNumber(offerRequest.phoneNumber);
        checkMissingOfferAmount(offerRequest.amount);
        checkMissingOfferMessage(offerRequest.message);
    }

    private void checkMissingOfferName(String name) {
        if (name == null) {
            throw new MissingOfferNameException();
        }
    }

    private void checkMissingOfferEmail(String email) {
        if (email == null) {
            throw new MissingOfferEmailException();
        }
    }

    private void checkMissingOfferPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new MissingOfferPhoneNumberException();
        }
    }

    private void checkMissingOfferAmount(Double amount) {
        if (amount == null) {
            throw new MissingOfferAmountException();
        }
    }

    private void checkMissingOfferMessage(String message) {
        if (message == null) {
            throw new MissingOfferMessageException();
        }
    }
}
