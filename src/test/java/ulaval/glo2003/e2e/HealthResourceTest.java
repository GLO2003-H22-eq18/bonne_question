package ulaval.glo2003.e2e;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ulaval.glo2003.Main;
import java.io.IOException;

import static io.restassured.RestAssured.*;


class HealthResourceTest {
    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @Test
    public void getHealth() {
        get("http://localhost:8080/health").then().statusCode(200);
    }
}