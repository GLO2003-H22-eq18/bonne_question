package ulaval.glo2003.e2e;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.Main;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

public class ProductResourceTest {

    private Seller defaultSeller;
    private int sellerId;

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @BeforeEach
    public void createDefaultSeller(){
        defaultSeller = new Seller("John Cena",
                    "What a chad!",
                    OffsetDateTime.now(Clock.systemUTC()),
                    LocalDate.parse("1977-04-23"),
                    new ArrayList<>());
    }






}
