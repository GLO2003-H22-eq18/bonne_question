package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.product.domain.ProductCategory.toStringList;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getProduct;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.exceptions.MissingProductDescriptionException;
import ulaval.glo2003.product.exceptions.MissingProductSuggestedPriceException;
import ulaval.glo2003.product.exceptions.MissingProductTitleException;
import ulaval.glo2003.product.ui.assemblers.ProductAssembler;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;
import ulaval.glo2003.product.ui.responses.ProductResponse;
import ulaval.glo2003.product.ui.responses.ProductSellerResponse;

class ProductAssemblerTest {

    private static final String TITLE = "Une roche";
    private static final String DESCRIPTION = "Un matériau solide";
    private static final Double SUGGESTED_PRICE = 500.0;
    private static final List<String> CATEGORIES = List.of("sports");
    private static final Double AMOUNT1 = 25.0;
    private static final Double AMOUNT2 = 75.0;
    private static ProductAssembler productAssembler;

    @BeforeAll
    public static void setUp() {
        productAssembler = new ProductAssembler();
    }

    @Test
    void
    givenProductRequestWithMissingTitle_whenCreatingProduct_thenMissingProductTitleException() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.description = DESCRIPTION;
        productRequest.categories = CATEGORIES;
        productRequest.suggestedPrice = SUGGESTED_PRICE;

        assertThrows(
                MissingProductTitleException.class,
                () -> productAssembler.checkProductRequestMissingParams(productRequest));
    }

    @Test
    void
    givenProductRequestWithMissingDescription_whenCreatingProduct_thenMissingProductDescriptionException() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = TITLE;
        productRequest.categories = CATEGORIES;
        productRequest.suggestedPrice = SUGGESTED_PRICE;

        assertThrows(
                MissingProductDescriptionException.class,
                () -> productAssembler.checkProductRequestMissingParams(productRequest));
    }

    @Test
    void
    givenProductRequestWithMissingSuggestedPrice_whenCreatingProduct_thenMissingProductSuggestedPriceException() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = TITLE;
        productRequest.description = DESCRIPTION;
        productRequest.categories = CATEGORIES;

        assertThrows(
                MissingProductSuggestedPriceException.class,
                () -> productAssembler.checkProductRequestMissingParams(productRequest));
    }

    @Test
    void givenProductWithNoOffer_whenCreateProductResponse_thenCorrectProductResponse() {
        Product product = getProduct();

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        assertThat(productResponse.id).isEqualTo(product.getId().toString());
        assertThat(productResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(productResponse.title).isEqualTo(product.getTitle());
        assertThat(productResponse.description).isEqualTo(product.getDescription());
        assertThat(productResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productResponse.categories).isEqualTo(toStringList(product.getCategories()));
        assertThat(productResponse.seller).isInstanceOf(ProductSellerResponse.class);
        assertThat(productResponse.offers).isInstanceOf(ProductOffersResponse.class);
    }

    @Test
    void givenProductWithOneOffer_whenCreateProductResponse_thenCorrectProductResponse() {
        Product product = getProduct();
        Offer offer = getOffer(AMOUNT1);
        product.addOffer(offer);

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        assertThat(productResponse.id).isEqualTo(product.getId().toString());
        assertThat(productResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(productResponse.title).isEqualTo(product.getTitle());
        assertThat(productResponse.description).isEqualTo(product.getDescription());
        assertThat(productResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productResponse.categories).isEqualTo(toStringList(product.getCategories()));
        assertThat(productResponse.seller).isInstanceOf(ProductSellerResponse.class);
        assertThat(productResponse.offers).isInstanceOf(ProductOffersResponse.class);
    }

    @Test
    void givenProductWithMultipleOffers_whenCreateProductResponse_thenCorrectProductResponse() {
        Product product = getProduct();
        Offer offer1 = getOffer(AMOUNT1);
        Offer offer2 = getOffer(AMOUNT2);
        product.addOffer(offer1);
        product.addOffer(offer2);

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        assertThat(productResponse.id).isEqualTo(product.getId().toString());
        assertThat(productResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(productResponse.title).isEqualTo(product.getTitle());
        assertThat(productResponse.description).isEqualTo(product.getDescription());
        assertThat(productResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productResponse.categories).isEqualTo(toStringList(product.getCategories()));
        assertThat(productResponse.seller).isInstanceOf(ProductSellerResponse.class);
        assertThat(productResponse.offers).isInstanceOf(ProductOffersResponse.class);
    }
}
