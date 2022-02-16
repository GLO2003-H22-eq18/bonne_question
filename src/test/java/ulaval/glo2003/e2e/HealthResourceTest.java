package ulaval.glo2003.e2e;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ulaval.glo2003.Main;
import ulaval.glo2003.SellerRequest;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.*;


class HealthResourceTest {

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @Test
    public void getHealth() {
        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/health")
                .then()
                .extract();

        int responseStatus = response.statusCode();

        assertThat(responseStatus).isEqualTo(200);
    }
}