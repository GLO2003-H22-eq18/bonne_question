package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.Exceptions.SellerNotFoundException;

public class SellerRepositoryTest {

    private SellerRepository sellerRepository;

    @BeforeEach
    public void setUp() {
        sellerRepository = new SellerRepository();
    }

    public Seller getSeller() {
        String name = "John Cena";
        String bio = "You can't see me, my time is now.";
        OffsetDateTime createdAt = OffsetDateTime.now();
        LocalDate birthDate = LocalDate.parse("1977-04-23");
        List<Product> products = new ArrayList<>();

        return new Seller(name, bio, createdAt, birthDate, products);
    }

    @Test
    void whenSearchingNullSellerInRepository_thenSellerNotFoundException() {
        Seller seller = getSeller();

        assertThrows(SellerNotFoundException.class, () -> sellerRepository.findById(seller.getId()));
    }

    @Test
    void whenSearchingExistingSellerInRepository_thenCorrectSeller() {
        Seller seller = getSeller();
        sellerRepository.save(seller);

        assertThat(sellerRepository.findById(seller.getId())).isEqualTo(seller);
    }
}
