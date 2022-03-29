package ulaval.glo2003.product.domain;

import java.util.regex.Pattern;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferAmountException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferEmailException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferMessageException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferNameException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferPhoneNumberException;
import ulaval.glo2003.product.ui.requests.OfferRequest;
import ulaval.glo2003.utils.StringUtil;

public class OfferFactory {
    private static int currentId = 0;

    public Offer create(Double productSuggestedPrice, OfferRequest offerRequest) {

        checkInvalidParam(productSuggestedPrice, offerRequest);

        return new Offer(
                String.valueOf(currentId++),
                (Math.round(offerRequest.amount * 100.0) / 100.0),
                offerRequest.message,
                offerRequest.name,
                offerRequest.email,
                offerRequest.phoneNumber
        );
    }

    private void checkInvalidParam(Double productSuggestedPrice, OfferRequest offerRequest) {
        validateEmail(offerRequest.email);
        validatePhoneNumber(offerRequest.phoneNumber);
        validateAmount(productSuggestedPrice, offerRequest.amount);
        validateMessage(offerRequest.message);
        validateName(offerRequest.name);
    }

    private void validateEmail(String email) {
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                        + "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (!(pattern.matcher(email).matches())) {
            throw new InvalidOfferEmailException();
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^[0-9]{11}$";

        Pattern pattern = Pattern.compile(phoneNumberRegex);
        if (!(pattern.matcher(phoneNumber).matches())) {
            throw new InvalidOfferPhoneNumberException();
        }
    }

    private void validateAmount(Double productSuggestedPrice, Double amount) {
        if (amount < productSuggestedPrice) {
            throw new InvalidOfferAmountException();
        }
    }

    private void validateMessage(String message) {
        if (StringUtil.removeEmptyChar(message).isEmpty()
                || message.length() < 100) {
            throw new InvalidOfferMessageException();
        }
    }

    private void validateName(String name) {
        if (StringUtil.removeEmptyChar(name).isEmpty()) {
            throw new InvalidOfferNameException();
        }
    }
}
