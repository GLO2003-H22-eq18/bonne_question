package ulaval.glo2003.e2e;

import static io.restassured.RestAssured.given;

public class HealthEnd2EndUtils {

    public static int getHealth() {
        return given()
                .when()
                .get("/health")
                .then()
                .extract().statusCode();
    }

}
