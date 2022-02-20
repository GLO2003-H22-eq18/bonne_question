package ulaval.glo2003.e2e;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.io.IOException;

import static io.restassured.RestAssured.*;

import static com.google.common.truth.Truth.*;
import static org.hamcrest.CoreMatchers.notNullValue;


class SellerResourceTest {

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @Test
    void givenSeller_whenMakingPOSTRequestToSellerEndpoint_thenCorrect() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = "1977-04-23";

        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .extract();

        int responseStatus = response.statusCode();
        String headerLocation = response.headers().getValue("Location");
        String headerLocationId = headerLocation.split("/")[4];

        assertThat(responseStatus).isEqualTo(201);
        assertThat(headerLocation).isEqualTo("http://localhost:8080/sellers/" + headerLocationId);
    }

    @Test
    void givenSeller_whenMakingPOSTRequestToSellerEndpointWithMissingField_thenMissingParameterError() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = null;

        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .extract();

        int responseStatus = response.statusCode();
        JsonPath responseJson = response.jsonPath();
        String description = responseJson.get("description");
        String errorCode = responseJson.get("code");

        assertThat(responseStatus).isEqualTo(400);
        assertThat(description).isNotEmpty();
        assertThat(errorCode).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    void givenSeller_whenMakingPOSTRequestToSellerEndpointWithInvalidField_thenInvalidParameterError() {
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
        String errorCode = responseJson.get("code");

        assertThat(responseStatus).isEqualTo(400);
        assertThat(description).isNotEmpty();
        assertThat(errorCode).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    void whenMakingGETRequestToSellerEndpointWithAbsentSellerId_thenItemNotFoundError() {
        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/sellers/1290412403")
                .then()
                .extract();

        JsonPath responseJson = response.body().jsonPath();
        String description = responseJson.get("description").toString();
        String errorCode = responseJson.get("code").toString();

        assertThat(description).isNotEmpty();
        assertThat(errorCode).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    void whenMakingGETRequestToSellerEndpoint_thenCorrect() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "Bob Rogers";
        sellerRequest.bio = "Not a chad!";
        sellerRequest.birthDate = "1985-03-22";
        String headerLocationId = given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .extract()
                .header("Location")
                .split("/")[4];

        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/sellers/" + headerLocationId)
                .then()
                .extract();
        int responseStatus = response.statusCode();
        JsonPath responseJson = response.jsonPath();
        String id = responseJson.get("id");
        String name = responseJson.get("name");
        String bio = responseJson.get("bio");

        assertThat(responseStatus).isEqualTo(200);
        assertThat(id).isEqualTo(headerLocationId);
        assertThat(name).isEqualTo("Bob Rogers");
        assertThat(bio).isEqualTo("Not a chad!");
    }

}