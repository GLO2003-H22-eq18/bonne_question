package ulaval.glo2003.product.domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.regex.Pattern;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferAmountException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferEmailException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferMessageException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferNameException;
import ulaval.glo2003.product.exceptions.offers.InvalidOfferPhoneNumberException;
import ulaval.glo2003.product.ui.requests.OfferRequest;
import ulaval.glo2003.utils.StringUtil;

public class ViewFactory {
    public View create() {
        return new View(
                new ObjectId(),
                OffsetDateTime.now(Clock.systemUTC())
        );
    }
}
