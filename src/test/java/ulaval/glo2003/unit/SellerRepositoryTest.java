package ulaval.glo2003.unit;

import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.Seller.Domain.SellerRepository;

import java.time.Clock;
import java.time.OffsetDateTime;

public class SellerRepositoryTest {

    private SellerRepository sellerRepository;

    private final OffsetDateTime SELLER_CREATED_AT_1 = OffsetDateTime.now(Clock.systemUTC());

    @BeforeEach
    public void setUp() {
        sellerRepository = new SellerRepository();
    }

    private void saveMultipleValidSellersToRepository() {
        // TODO : create seller attributes and save here
        // sellerRepository.save();
        // sellerRepository.save();
    }
}
