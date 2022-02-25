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
        SellerRequest sellerRequest = createValidSeller();

        ExtractableResponse<Response> response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(extractLocationId(response)).isNotNull();
        assertThat(extractLocationId(response)).isNotEmpty();
    }

    @Test
    void givenSellerRequestWithMissingParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createMissingParamSeller();

        ExtractableResponse<Response> response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).isNotNull();
    }

    @Test
    void givenSellerRequestWithInvalidParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createInvalidParamSeller();

        ExtractableResponse<Response> response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).isNotNull();
    }

    @Test
    void canCreateProductIsAddedToSeller() {
        ProductRequest productRequest = createProduct();

        ExtractableResponse<Response> response = createProductResource(productRequest, createSellerGetId());

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(extractLocationId(response)).isNotNull();
        assertThat(extractLocationId(response)).isNotEmpty();
    }

}