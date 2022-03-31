package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.seller.ui.assemblers.SellerProductAssembler;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;

public class SellerProductAssemblerTest {

    private static SellerProductAssembler sellerProductAssembler;

    @BeforeAll
    public static void setUp() {
        sellerProductAssembler = new SellerProductAssembler();
    }

    public Product getProduct() {
        String title = "Mister Clean";
        String description = "Wow, so good!";
        Double suggestedPrice = 34d;
        List<ProductCategory> categories = new ArrayList<>();
        ObjectId sellerId = new ObjectId();
        String sellerName = "John Doe";
        ObjectId id = new ObjectId();
        List<Offer> offers = new ArrayList<>();

        return new Product(title, description, suggestedPrice, categories, sellerId, sellerName, id, offers, OffsetDateTime.now(Clock.systemUTC()));
    }

    @Test
    void givenSeller_whenCreateSellerResponse_thenCorrectSellerResponse() {
        Product product = getProduct();

        SellerProductResponse sellerProductResponse =
                sellerProductAssembler.createSellerProductResponse(product);

        assertThat(sellerProductResponse.id).isEqualTo(product.getId().toString());
        assertThat(sellerProductResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(sellerProductResponse.title).isEqualTo(product.getTitle());
        assertThat(sellerProductResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(sellerProductResponse.description).isEqualTo(product.getDescription());
        assertThat(sellerProductResponse.categories).isEqualTo(product.getCategories());
        assertThat(sellerProductResponse.offers.count).isEqualTo(0);
        assertThat(sellerProductResponse.offers.mean).isEqualTo(null);
    }
}
