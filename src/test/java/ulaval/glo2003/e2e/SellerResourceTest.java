package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.A_INVALID_ID;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatPostResponseIsValid;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsInvalidParamError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsItemNotFoundError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsMissingParamError;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithRandomOffersFromSellerGetId;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.NUMBER_OF_OFFERS;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.addProductToSellerGetId;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.assertThatSellerResponseFieldsAreValid;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.assertThatSellerWithProductResponseFieldsAreValid;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createSellerResource;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createSellerWithInvalidAge;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createSellerWithInvalidBio;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createSellerWithInvalidName;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createSellerWithMissingParams;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createValidSeller;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createValidSellerGetId;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.getCurrentSellerById;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.getSellerById;

import io.restassured.response.Response;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.seller.ui.requests.SellerRequest;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;
import ulaval.glo2003.seller.ui.responses.SellerResponse;

@DisplayName("Seller Resource")
class SellerResourceTest {

    public static HttpServer server;

    @BeforeAll
    public static void startServer() throws IOException {
        server = Main.startServer();
        server.start();
    }

    @AfterAll
    public static void closeServer() {
        server.shutdownNow();
    }

    @Nested
    @DisplayName("WHEN creating seller")
    class WhenCreatingSeller {
        @DisplayName("GIVEN valid request THEN returns with status 201")
        @Test
        void givenValidSellerRequest_whenCreatingSeller_thenSellerCreatedWithStatus201() {
            SellerRequest sellerRequest = createValidSeller();

            Response response = createSellerResource(sellerRequest);

            assertThatPostResponseIsValid(response);
        }

        @DisplayName("GIVEN missing params THEN returns error 400")
        @Test
        void givenSellerRequestWithMissingParams_whenCreatingSeller_thenReturnsError400() {
            SellerRequest sellerRequest = createSellerWithMissingParams();

            Response response = createSellerResource(sellerRequest);

            assertThatResponseIsMissingParamError(response);
        }

        @DisplayName("GIVEN invalid name THEN returns error 400")
        @Test
        void givenSellerRequestWithInvalidName_whenCreatingSeller_thenReturnsError400() {
            SellerRequest sellerRequest = createSellerWithInvalidName();

            Response response = createSellerResource(sellerRequest);

            assertThatResponseIsInvalidParamError(response);
        }

        @DisplayName("GIVEN invalid bio THEN returns error 400")
        @Test
        void givenSellerRequestWithInvalidBio_whenCreatingSeller_thenReturnsError400() {
            SellerRequest sellerRequest = createSellerWithInvalidBio();

            Response response = createSellerResource(sellerRequest);

            assertThatResponseIsInvalidParamError(response);
        }

        @DisplayName("GIVEN invalid age THEN returns error 400")
        @Test
        void givenSellerRequestWithInvalidAge_whenCreatingSeller_thenReturnsError400() {
            SellerRequest sellerRequest = createSellerWithInvalidAge();

            Response response = createSellerResource(sellerRequest);

            assertThatResponseIsInvalidParamError(response);
        }
    }

    @Nested
    @DisplayName("WHEN getting seller")
    class WhenGettingSeller {
        @DisplayName("GIVEN invalid id THEN returns error 404")
        @Test
        void givenInvalidSellerId_whenGettingSeller_thenReturnsError404() {
            Response response = getSellerById(A_INVALID_ID);

            assertThatResponseIsItemNotFoundError(response);
        }

        @DisplayName("GIVEN valid id THEN returns seller with status 200")
        @Test
        void givenValidSellerId_whenGettingSeller_thenReturnsWithStatus200() {
            String sellerId = createValidSellerGetId();

            Response response = getSellerById(sellerId);
            SellerResponse sellerResponse = response.as(SellerResponse.class);

            assertThatSellerResponseFieldsAreValid(sellerResponse, sellerId);

        }
    }

    @Nested
    @DisplayName("WHEN adding a new product to seller")
    class WhenAddingProductToSeller {
        @DisplayName("THEN returns seller with created product and status 200")
        @Test
        void givenValidSellerIdWithProduct_whenGettingSeller_thenReturnsSellerWithProductAndStatus200() {
            String sellerId = createValidSellerGetId();
            String productId = addProductToSellerGetId(sellerId);

            Response response = getSellerById(sellerId);
            SellerResponse sellerResponse = response.as(SellerResponse.class);

            assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThatSellerWithProductResponseFieldsAreValid(sellerResponse,
                    sellerId, productId);
        }
    }

    @Nested
    @DisplayName("WHEN getting current seller")
    class WhenGettingCurrentSeller {

        @DisplayName("GIVEN seller without products THEN returns current seller and status 200")
        @Test
        void givenValidSellerIdWithoutProducts_whenGettingCurrentSeller_thenReturnsCurrentSellerAndStatus200() {
            // TODO
        }

        @DisplayName("GIVEN seller with products but no offers THEN returns current seller and status 200")
        @Test
        void givenValidSellerIdWithProductsWithoutOffers_whenGettingCurrentSeller_thenReturnsCurrentSellerWithProductsAndStatus200() {
            // TODO
        }

        @DisplayName("GIVEN seller with products and offers THEN returns current seller and status 200")
        @Test
        void givenValidSellerIdWithProductsWithOffers_whenGettingCurrentSeller_thenReturnsCurrentSellerWithProductAndOffersAndStatus200() {
            // TODO
            //String sellerId = createValidSellerGetId();
            //String productId = createProductWithRandomOffersFromSellerGetId(sellerId, NUMBER_OF_OFFERS);

            //Response response = getCurrentSellerById(sellerId);
            //CurrentSellerResponse sellerResponse = response.as(CurrentSellerResponse.class);

            //assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        }
    }
}
