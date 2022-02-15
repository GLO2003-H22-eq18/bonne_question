package ulaval.glo2003.e2e;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.SellerRequest;

import java.io.IOException;

import static io.restassured.RestAssured.*;

import static com.google.common.truth.Truth.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


class SellerResourceTest {

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }


    @Test
    void givenSeller_whenMakingPostRequestToSellerEndpoint_thenCorrect() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = "1977-04-23";

        given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .assertThat()
                .statusCode(201)
                .header("Location", "http://localhost:8080/sellers/0");
    }

    @Test
    void givenSeller_whenMakingPostRequestToSellerEndpointWithMissingField_thenMissingParameterError() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = null;

        given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .assertThat()
                .statusCode(400)
                .body("description", notNullValue())
                .body("errorCode", equalTo("MISSING_PARAMETER"));
    }

    @Test
    void givenSeller_whenMakingPostRequestToSellerEndpointWithInvalidField_thenInvalidParameterError() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "\n\t     000";
        sellerRequest.birthDate = "2000-01-01";

        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .extract();

        int responseStatus = response.statusCode();
        JsonPath responseJson = response.jsonPath();
        String description = responseJson.get("description");
        String errorCode = responseJson.get("errorCode");

        assertThat(responseStatus).isEqualTo(400);
        assertThat(description).isNotEmpty();
        assertThat(errorCode).isEqualTo("INVALID_PARAMETER");
    }
}