package ulaval.glo2003.product.domain;

import java.util.regex.Pattern;
import ulaval.glo2003.product.ui.OfferRequest;
import ulaval.glo2003.utils.StringUtil;

public class OfferFactory {
    private static int currentId = 0;

    public Offer create(Double productSuggestedPrice, OfferRequest offerRequest) {

        checkInvalidParam(productSuggestedPrice, offerRequest);

        return new Offer(
                String.valueOf(currentId++),
                offerRequest.amount,
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

        Pattern pat = Pattern.compile(emailRegex);
        if (!(pat.matcher(email).matches())) {
            //TODO : Add exception for Offer
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!(phoneNumber.matches("[0-9]+")) || !(phoneNumber.length() == 11)) {
            //TODO : Add exception for Offer
        }
    }

    private void validateAmount(Double productSuggestedPrice, Double amount) {
        if (amount < productSuggestedPrice) {
            //TODO : Add exception for Offer
        }
    }

    private void validateMessage(String message) {
        if (StringUtil.removeEmptyChar(message).isEmpty()
                || message.length() < 100) {
            //TODO : Add exception for Offer
        }
    }

    private void validateName(String name) {
        if (StringUtil.removeEmptyChar(name).isEmpty()) {
            //TODO : Add exception for Offer
        }
    }
}
