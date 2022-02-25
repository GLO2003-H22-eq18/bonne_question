package ulaval.glo2003.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.*;
import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

public class ProductFactoryTest {

    private static final String TITLE = "Une roche";
    private static final String DESCRIPTION = "Un matériau solide";
    private static final Double SUGGESTED_PRICE = 500.0;
    private static final List<String> CATEGORIES = List.of("sports");

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
    void test2(){

    }
}
