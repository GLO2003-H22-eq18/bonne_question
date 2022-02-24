package ulaval.glo2003.e2e;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndUtils.*;


class End2EndTests {

    private static final String SELLER_HEADER_NAME = "X-Seller-Id";

    @BeforeAll
    public static void setup() throws IOException {
        Main.main(new String[] {});
    }

    @Test
    void healthReturnsOk() {
        int responseStatus = getHealth();

        assertThat(responseStatus).isEqualTo(200);
    }

    @Test
    void canCreateSellerHasLocationHeader() {
        SellerRequest sellerRequest = createSeller();

        ExtractableResponse<Response> sellerResponse = createResource("/sellers", sellerRequest);
        String sellerId = sellerResponse.header("Location").split("/")[4];

        assertThat(sellerResponse.statusCode()).isEqualTo(201);
        assertThat(sellerId).isNotNull();
        assertThat(sellerId).isNotEmpty();
    }

    @Test
    void canCreateProductIsAddedToSeller() {
        ProductRequest productRequest = createProduct();
        Header productSeller = new Header(SELLER_HEADER_NAME, createSellerGetId());

        ExtractableResponse<Response> productResponse = createResource("/products", productSeller, productRequest);
        String productId = productResponse.header("Location").split("/")[4];

        assertThat(productResponse.statusCode()).isEqualTo(201);
        assertThat(productId).isNotNull();
        assertThat(productId).isNotEmpty();
    }

}