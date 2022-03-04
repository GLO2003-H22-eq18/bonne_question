package ulaval.glo2003.e2e;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;


public class ProductResourceTest {

    private Seller defaultSeller;
    private int sellerId;

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

    ProductRequest createProductRequest(String title, String description, Double suggestedPrice, List<String> categories){
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = title;
        productRequest.description = description;
        productRequest.suggestedPrice = suggestedPrice;
        productRequest.categories = categories;
        return productRequest;
    }

    String name = "John Cena";
    String bio = "You can't see me, my time is now.";
    OffsetDateTime createdAt = OffsetDateTime.now();
    LocalDate birthDate = LocalDate.parse("1977-04-23");
    List<Product> products = new ArrayList<>();
    Seller seller = new Seller(name, bio, createdAt, birthDate, products);



   /* @BeforeAll
    public void createDefaultSeller (){
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "name";
        sellerRequest.bio =  "bio";
        sellerRequest.birthDate = "1977-04-23";

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

        JsonPath responseJson = response.jsonPath();
        String id = responseJson.get("id");
    }*/




    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpoint_thenCorrect(){
    //TODO ADD X-Seller-ID in header
        /*SellerRequest sellerRequest = createSellerRequest();*/

        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "name";
        sellerRequest.bio =  "bio";
        sellerRequest.birthDate = "1977-04-23";

        String headerLocationIdSeller = given().contentType(ContentType.JSON)
                .body(sellerRequest)
                .when()
                .post("http://localhost:8080/sellers")
                .then()
                .extract()
                .header("Location")
                .split("/")[4];

        ExtractableResponse<Response> response1 = given().contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/sellers/" + headerLocationIdSeller)
                .then()
                .extract();

        JsonPath responseJson = response1.jsonPath();
        String id = responseJson.get("id");


        ProductRequest productRequest = createProductRequest("Clever title",
                "Clever description",
                5.0,
                new ArrayList<>());


        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .header("X-Seller-Id", id) //TODO sellerRequest.getID();
                .and()
                .body(productRequest)
                .when()
                .post("http://localhost:8080/products")
                .then()
                .extract();

        int responseStatus = response.statusCode();
        String headerLocation = response.headers().getValue("Location");
        String headerLocationId = headerLocation.split("/")[4];

        assertThat(responseStatus).isEqualTo(201);
        assertThat(headerLocation).isEqualTo("http://localhost:8080/products/" + headerLocationId);
    }

    @Test
    void whenMakingGETResquestToProductEndpoint_thenCorrect(){
        //TODO 200
    }


    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpointWithMissingField_thenMissingParameterError() {
        //TODO
    }

    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpointWithInvalidField_thenInvalidParameterError() {
        //TODO
    }

    @Test
    void whenMakingGETRequestToProductEndpointWithAbsentProductId_thenItemNotFoundError() {
        //TODO
    }

    @Test
    void whenMakingGETResquestToProductEndpointWithFilter_thenCorrect(){
        //TODO 200
    }







}
