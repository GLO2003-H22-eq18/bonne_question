package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.product.domain.ProductCategory.toCategoriesList;
import static ulaval.glo2003.product.domain.ProductCategory.toStringList;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.exceptions.MissingProductDescriptionException;
import ulaval.glo2003.product.exceptions.MissingProductSuggestedPriceException;
import ulaval.glo2003.product.exceptions.MissingProductTitleException;
import ulaval.glo2003.product.ui.assemblers.ProductAssembler;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.product.ui.responses.ProductResponse;

class ProductAssemblerTest {

    private static ProductAssembler productAssembler;

    private static final String TITLE = "Une roche";
    private static final String INVALID_TITLE = "";
    private static final String DESCRIPTION = "Un matériau solide";
    private static final String INVALID_DESCRIPTION = "  \n\t";
    private static final Double SUGGESTED_PRICE = 500.0;
    private static final String INVALID_SUGGESTED_PRICE = "90a";
    private static final List<String> CATEGORIES = List.of("sports");
    private static final List<String> EMPTY_CATEGORIES = List.of();
    private static final List<String> INVALID_CATEGORIES = List.of("sports", "invalid");

    @BeforeAll
    public static void setUp() {
        productAssembler = new ProductAssembler();
    }

    public Product getProduct() {
        String title = "Clean Stuff";
        String description = "The cleanest of all the clean stuff.";
        Double suggestedPrice = 32d;
        String sellerId = "0";
        String sellerName = "John Doe";
        String id = "1";

        List<String> categoriesString = new ArrayList<>();
        categoriesString.add("beauty");
        categoriesString.add("sports");
        List<ProductCategory> categories = toCategoriesList(categoriesString);

        return new Product(id, title, description, suggestedPrice, categories, sellerId, sellerName);
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
    void givenProduct_whenCreateProductResponse_thenCorrectProductResponse() {
        Product product = getProduct();

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        assertThat(productResponse.id).isEqualTo(product.getId());
        assertThat(productResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(productResponse.title).isEqualTo(product.getTitle());
        assertThat(productResponse.description).isEqualTo(product.getDescription());
        assertThat(productResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productResponse.categories).isEqualTo(toStringList(product.getCategories()));
        assertThat(productResponse.seller.id).isEqualTo(product.getSellerId());
        assertThat(productResponse.seller.name).isEqualTo(product.getSellerName());
        assertThat(productResponse.offers.count).isEqualTo(product.getCount());
    }
}
