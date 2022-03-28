package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.assemblers.SellerAssembler;
import ulaval.glo2003.seller.ui.assemblers.SellerProductAssembler;
import ulaval.glo2003.seller.ui.responses.SellerResponse;

class SellerAssemblerTest {

    private static SellerAssembler sellerAssembler;

    @BeforeAll
    public static void setUp() {
        sellerAssembler = new SellerAssembler();
    }

    public Seller getSeller() {
        String name = "John Cena";
        String bio = "You can't see me, my time is now.";
        OffsetDateTime createdAt = OffsetDateTime.now();
        LocalDate birthDate = LocalDate.parse("1977-04-23");
        List<Product> products = new ArrayList<>();
        int id = 1;

        return new Seller(name, bio, createdAt, birthDate, products, id);
    }

    @Test
    void givenSeller_whenCreateSellerResponse_thenCorrectSellerResponse() {
        Seller seller = getSeller();

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);

        assertThat(sellerResponse.id).isEqualTo(seller.getId());
        assertThat(sellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(sellerResponse.name).isEqualTo(seller.getName());
        assertThat(sellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(sellerResponse.products).isEqualTo(seller.getProducts());
    }
}
