package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Exceptions.InvalidSellerBioException;
import ulaval.glo2003.Seller.Exceptions.InvalidSellerBirthdateException;
import ulaval.glo2003.Seller.Exceptions.InvalidSellerNameException;
import ulaval.glo2003.Seller.Exceptions.MissingSellerBioException;
import ulaval.glo2003.Seller.Exceptions.MissingSellerBirthdateException;
import ulaval.glo2003.Seller.Exceptions.MissingSellerNameException;
import ulaval.glo2003.Seller.UI.SellerRequest;

public class SellerFactoryTest {

    private static final String NAME = "Beau Nom";
    private static final String BIO = "Belle Bio";
    private static final String BIRTHDATE = "1979-12-12";
    private static final String INVALID_NAME = "\n\t";
    private static final String INVALID_BIO = "\n\t";
    private static final String INVALID_BIRTHDATE = "2015-12-12";

    private static SellerFactory sellerFactory;

    @BeforeAll
    public static void setUp() {
        sellerFactory = new SellerFactory();
    }

    public SellerRequest createSellerRequest(String name, String bio, String birthdate) {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = name;
        sellerRequest.bio = bio;
        sellerRequest.birthDate = birthdate;

        return sellerRequest;
    }

    @Test
    public void givenSeller_whenCreatingSeller_thenCorrectSeller() {
        SellerRequest sellerRequest = createSellerRequest(NAME, BIO, BIRTHDATE);

        Seller seller = sellerFactory.create(sellerRequest);

        assertThat(seller.getName()).isEqualTo(NAME);
        assertThat(seller.getBio()).isEqualTo(BIO);
        assertThat(seller.getBirthDate()).isEqualTo(LocalDate.parse(BIRTHDATE));
    }

    @Test
    public void
    givenSellerRequestWithMissingName_whenCreatingSeller_thenMissingSellerNameException() {
        SellerRequest sellerRequest = createSellerRequest(null, BIO, BIRTHDATE);

        assertThrows(MissingSellerNameException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void
    givenSellerRequestWithMissingBio_whenCreatingSeller_thenMissingSellerBioException() {
        SellerRequest sellerRequest = createSellerRequest(NAME, null, BIRTHDATE);

        assertThrows(MissingSellerBioException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void
    givenSellerRequestWithMissingBirthdate_whenCreatingSeller_thenMissingSellerBirthdateException() {
        SellerRequest sellerRequest = createSellerRequest(NAME, BIO, null);

        assertThrows(
                MissingSellerBirthdateException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void
    givenSellerRequestWithMissingName_whenCreatingSeller_thenInvalidSellerNameException() {
        SellerRequest sellerRequest = createSellerRequest(INVALID_NAME, BIO, BIRTHDATE);

        assertThrows(InvalidSellerNameException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void
    givenSellerRequestWithMissingBio_whenCreatingSeller_thenInvalidSellerBioException() {
        SellerRequest sellerRequest = createSellerRequest(NAME, INVALID_BIO, BIRTHDATE);

        assertThrows(InvalidSellerBioException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void
    givenSellerRequestWithMissingBirthdate_whenCreatingSeller_thenInvalidSellerBirthdateException() {
        SellerRequest sellerRequest = createSellerRequest(NAME, BIO, INVALID_BIRTHDATE);

        assertThrows(
                InvalidSellerBirthdateException.class, () -> sellerFactory.create(sellerRequest));
    }

    @Test
    public void givenTwoSellerRequest_whenCreatingSellers_thenCorrectSellerWithDifferentId() {
        SellerRequest sellerRequest1 = createSellerRequest(NAME, BIO, BIRTHDATE);
        SellerRequest sellerRequest2 = createSellerRequest(NAME, BIO, BIRTHDATE);

        Seller seller1 = sellerFactory.create(sellerRequest1);
        Seller seller2 = sellerFactory.create(sellerRequest2);

        assertThat(seller1.getId()).isNotEqualTo(seller2.getId());
    }
}
