package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Seller.UI.SellerRequest;


class SellerResourceTest {

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    SellerRequest createSellerRequest(String name, String bio, String birthDate) {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = name;
        sellerRequest.bio = bio;
        sellerRequest.birthDate = birthDate;
        return sellerRequest;
    }

    @Test
    void givenSellerRequest_whenMakingPOSTRequestToSellerEndpoint_thenCorrect() {
        SellerRequest sellerRequest =
                createSellerRequest("John Cena", "What a chad!", "1977-04-23");

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
    void givenSellerRequest_whenMakingPOSTRequestToSellerEndpointWithMissingField_thenMissingParameterError() {
        SellerRequest sellerRequest = createSellerRequest("John Cena", "What a chad!", null);

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
        SellerRequest sellerRequest =
                createSellerRequest("John Cena", "\n\t     000", "2000-01-01");

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
    void givenSeller_whenMakingPOSTRequestToSellerEndpointWithInvalidBirthdateField_thenInvalidParameterError() {
        SellerRequest sellerRequest = createSellerRequest("John Cena", "Sick bio!", "2015-01-01");

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
        int responseStatus = response.statusCode();
        JsonPath responseJson = response.body().jsonPath();
        String description = responseJson.get("description").toString();
        String errorCode = responseJson.get("code").toString();

        assertThat(responseStatus).isEqualTo(404);
        assertThat(description).isNotEmpty();
        assertThat(errorCode).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    void whenMakingGETRequestToSellerEndpoint_thenCorrect() {
        SellerRequest sellerRequest =
                createSellerRequest("Bob Rogers", "Not a chad!", "1985-03-22");
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
