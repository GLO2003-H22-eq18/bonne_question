package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.product.domain.ProductCategory.toStringList;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getProduct;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;
import ulaval.glo2003.seller.ui.assemblers.SellerProductAssembler;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;

public class SellerProductAssemblerTest {

    private static SellerProductAssembler sellerProductAssembler;

    @BeforeAll
    public static void setUp() {
        sellerProductAssembler = new SellerProductAssembler();
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
        assertThat(sellerProductResponse.categories).isEqualTo(
                toStringList(product.getCategories()));
        assertThat(sellerProductResponse.offers).isInstanceOf(ProductOffersResponse.class);
    }
}
