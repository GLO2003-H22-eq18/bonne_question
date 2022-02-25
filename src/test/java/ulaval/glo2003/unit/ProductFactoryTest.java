package ulaval.glo2003.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Exceptions.*;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

public class ProductFactoryTest {

    private static final String TITLE = "Une roche";
    private static final String INVALID_TITLE = "";
    private static final String DESCRIPTION = "Un matériau solide";
    private static final String INVALID_DESCRIPTION = "  \n\t";
    private static final Double SUGGESTED_PRICE = 500.0;
    private static final Double INVALID_SUGGESTED_PRICE = 0.0;
    private static final List<String> CATEGORIES = List.of("sports");
    private static final List<String> EMPTY_CATEGORIES = List.of();
    private static final List<String> INVALID_CATEGORIES = List.of("sports", "invalid");

    private static ProductFactory productFactory;
    private static Seller productSeller;

    @BeforeAll
    static void setUp(){
        productFactory = new ProductFactory();
        productSeller = new Seller("John Cena",
                "What a chad!",
                OffsetDateTime.now(Clock.systemUTC()),
                LocalDate.parse("1977-04-23"),
                new ArrayList<>());
    }

    ProductRequest createProductRequest(String title, String description, Double suggestedPrice, List<String> categories){
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = title;
        productRequest.description = description;
        productRequest.suggestedPrice = suggestedPrice;
        productRequest.categories = categories;

        return productRequest;
    }


    @Test
    void givenProductRequest_whenCreatingProduct_thenCorrectProduct(){
        ProductRequest productRequest = createProductRequest(TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        Product product = productFactory.create(productSeller, productRequest);

        assertThat(product.getTitle()).isEqualTo(TITLE);
        assertThat(product.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(product.getSuggestedPrice()).isEqualTo(SUGGESTED_PRICE);
        assertThat(toStringList(product.getCategories())).isEqualTo(CATEGORIES);
        assertThat(product.getSellerId()).isEqualTo(productSeller.getId());
        assertThat(product.getSellerName()).isEqualTo(productSeller.getName());
    }

    @Test
    void givenProductRequestWithMissingTitle_whenCreatingProduct_thenMissingProductTitleException(){
        ProductRequest productRequest = createProductRequest(null, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertThrows(MissingProductTitleException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithMissingDescription_whenCreatingProduct_thenMissingProductDescriptionException(){
        ProductRequest productRequest = createProductRequest(TITLE, null, SUGGESTED_PRICE, CATEGORIES);

        assertThrows(MissingProductDescriptionException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithMissingSuggestedPrice_whenCreatingProduct_thenMissingProductSuggestedPriceException(){
        ProductRequest productRequest = createProductRequest(TITLE, DESCRIPTION, null, CATEGORIES);

        assertThrows(MissingProductSuggestedPriceException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithInvalidTitle_whenCreatingProduct_thenInvalidProductTitleException(){
        ProductRequest productRequest = createProductRequest(INVALID_TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertThrows(InvalidProductTitleException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithInvalidDescription_whenCreatingProduct_thenInvalidProductDescriptionException(){
        ProductRequest productRequest = createProductRequest(TITLE, INVALID_DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        assertThrows(InvalidProductDescriptionException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithInvalidSuggestedPrice_whenCreatingProduct_thenInvalidProductSuggestedPriceException(){
        ProductRequest productRequest = createProductRequest(TITLE, DESCRIPTION, INVALID_SUGGESTED_PRICE, CATEGORIES);

        assertThrows(InvalidProductSuggestedPriceException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithInvalidCategories_whenCreatingProduct_thenInvalidProductCategoriesException(){
        ProductRequest productRequest = createProductRequest(TITLE, DESCRIPTION, SUGGESTED_PRICE, INVALID_CATEGORIES);

        assertThrows(InvalidProductCategoriesException.class,
                     () -> productFactory.create(productSeller, productRequest));
    }

    @Test
    void givenProductRequestWithEmptyCategories_whenCreatingProduct_thenCorrectProduct(){
        ProductRequest productRequest = createProductRequest(TITLE, DESCRIPTION, SUGGESTED_PRICE, EMPTY_CATEGORIES);

        Product product = productFactory.create(productSeller, productRequest);

        assertThat(product.getTitle()).isEqualTo(TITLE);
        assertThat(product.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(product.getSuggestedPrice()).isEqualTo(SUGGESTED_PRICE);
        assertThat(toStringList(product.getCategories())).isEqualTo(EMPTY_CATEGORIES);
        assertThat(product.getSellerId()).isEqualTo(productSeller.getId());
        assertThat(product.getSellerName()).isEqualTo(productSeller.getName());
    }

    @Test
    void givenTwoProductRequest_whenCreatingProducts_thenCorrectProductWithDifferentId(){
        ProductRequest productRequest1 = createProductRequest(TITLE, DESCRIPTION, SUGGESTED_PRICE, EMPTY_CATEGORIES);
        ProductRequest productRequest2 = createProductRequest(TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORIES);

        Product product1 = productFactory.create(productSeller, productRequest1);
        Product product2 = productFactory.create(productSeller, productRequest2);

        assertThat(product1.getId()).isNotEqualTo(product2.getId());
    }
}
