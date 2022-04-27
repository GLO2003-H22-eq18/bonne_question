package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.infrastructure.assemblers.ViewModelAssembler;
import ulaval.glo2003.product.infrastructure.repository.MongoProductsRepository;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.exceptions.SellerNotFoundException;
import ulaval.glo2003.product.infrastructure.assemblers.OfferModelAssembler;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.seller.infrastructure.assemblers.SellerModelAssembler;
import ulaval.glo2003.seller.infrastructure.repository.MongoSellersRepository;

public class SellerRepositoryTest {

    private static final ObjectId INVALID_SELLER_ID = new ObjectId();
    private SellerRepository sellerRepository;
    public static ApplicationContext applicationContext = new ApplicationContext();

    @BeforeEach
    public void setUp() {
        OfferModelAssembler offerModelAssembler = new OfferModelAssembler();
        ViewModelAssembler viewModelAssembler = new ViewModelAssembler();
        ProductModelAssembler productModelAssembler = new ProductModelAssembler(offerModelAssembler, viewModelAssembler);
        SellerModelAssembler sellerModelAssembler = new SellerModelAssembler(productModelAssembler);
        this.sellerRepository = new MongoSellersRepository(applicationContext, sellerModelAssembler, productModelAssembler);
    }

    public Seller getSeller() {
        String name = "John Cena";
        String bio = "You can't see me, my time is now.";
        OffsetDateTime createdAt = OffsetDateTime.now();
        LocalDate birthDate = LocalDate.parse("1977-04-23");
        List<Product> products = new ArrayList<>();
        ObjectId id = new ObjectId();

        return new Seller(id, name, bio, createdAt, birthDate, products);
    }

    @Test
    void whenSearchingNullSellerInRepository_thenSellerNotFoundException() {
        assertThrows(SellerNotFoundException.class,
                () -> sellerRepository.findById(INVALID_SELLER_ID));
    }

    @Test
    void whenSearchingExistingSellerInRepository_thenCorrectSeller() {
        Seller seller = getSeller();
        sellerRepository.save(seller);

        assertThat(sellerRepository.findById(seller.getId()).getName()).isEqualTo(seller.getName());
        assertThat(sellerRepository.findById(seller.getId()).getBio()).isEqualTo(seller.getBio());
        assertThat(sellerRepository.findById(seller.getId()).getBirthDate()).isEqualTo(seller.getBirthDate());
        assertThat(sellerRepository.findById(seller.getId()).getProducts()).isEqualTo(seller.getProducts());
        assertThat(sellerRepository.findById(seller.getId()).getCreatedAt()).isEqualTo(seller.getCreatedAt());
    }
}
