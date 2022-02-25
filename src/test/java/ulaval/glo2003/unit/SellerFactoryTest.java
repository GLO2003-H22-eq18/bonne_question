package ulaval.glo2003.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.Exceptions.*;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SellerFactoryTest {

    private static final String NAME = "Beau Nom";
    private static final String BIO = "Belle Bio";
    private static final String BIRTHDATE = "1979-12-12";

    private static SellerFactory sellerFactory;
    private static  Seller seller;

    @BeforeAll
    static void setup(){
        sellerFactory = new SellerFactory();
        seller = new Seller("Clever name",
                "Clever Bio",
                OffsetDateTime.now(Clock.systemUTC()),
                LocalDate.parse("1977-04-23"),
                new ArrayList<>());
    }

    SellerRequest createSellerRequest(String name, String bio, String birthdate){
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = name;
        sellerRequest.bio = bio;
        sellerRequest.birthDate = birthdate;

        return sellerRequest;
    }

    @Test
    void givenSeller_whenCreatingSeller_thenCorrectSeller(){
        SellerRequest sellerRequest = createSellerRequest(NAME, BIO, BIRTHDATE);

        Seller seller = sellerFactory.create(sellerRequest);

        assertThat(seller.getName()).isEqualTo(NAME);
        assertThat(seller.getBio()).isEqualTo(BIO);
        assertThat(seller.getBirthDate()).isEqualTo(BIRTHDATE);
    }


}
