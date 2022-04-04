package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.HealthEnd2EndUtils.getHealth;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.Main;

@DisplayName("Health Resource")
public class HealthResourceTest {

    public static HttpServer server;
    public static ApplicationContext applicationContext = new ApplicationContext();

    @BeforeAll
    public static void startServer() throws IOException {
        server = Main.startServer(applicationContext);
        server.start();
    }

    @AfterAll
    public static void closeServer() {
        server.shutdownNow();
    }

    @Nested
    @DisplayName("WHEN getting health")
    class WhenGettingHealth {
        @DisplayName("THEN returns status 200 ok")
        @Test
        void getHealthReturnsWithStatus200() {
            int responseStatus = getHealth();

            assertThat(responseStatus).isEqualTo(HttpStatus.SC_OK);
        }
    }


}
