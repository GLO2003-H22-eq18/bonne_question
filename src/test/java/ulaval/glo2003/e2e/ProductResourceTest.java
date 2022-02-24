package ulaval.glo2003.e2e;

import com.fasterxml.jackson.core.JsonParseException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResource;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;


public class ProductResourceTest {

    private Seller defaultSeller;
    private int sellerId;

    ProductRequest createProductRequest(String title, String description, Double suggestedPrice, List<String> categories){
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = title;
        productRequest.description = description;
        productRequest.suggestedPrice = suggestedPrice;
        productRequest.categories = categories;
        return productRequest;
    }

    SellerRequest createSellerRequest(){
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "name";
        sellerRequest.bio =  "bio";
        sellerRequest.birthDate = "1977-04-23";
        return sellerRequest;
    }

    String name = "John Cena";
    String bio = "You can't see me, my time is now.";
    OffsetDateTime createdAt = OffsetDateTime.now();
    LocalDate birthDate = LocalDate.parse("1977-04-23");
    List<Product> products = new ArrayList<>();
    Seller seller = new Seller(name, bio, createdAt, birthDate, products);


    /*@BeforeAll
    public static void setup(){
        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerResource sellerResource = new SellerResource(sellerRepository, sellerFactory);

        //RestAssured.basePath = "/products";
    }*/

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpoint_thenCorrect(){
        ProductRequest productRequest = createProductRequest("Clever title",
                "Clever description",
                5.0,
                new ArrayList<>());

        SellerRequest sellerRequest = createSellerRequest();


        ExtractableResponse<Response> response = given().contentType(ContentType.JSON)
                .header("X-Seller-Id", seller.getId()) //TODO sellerRequest.getID();
                .and()
                .body(productRequest)
                .when()
                .post("http://localhost:8080/products")
                .then()
                .extract();

        int responseStatus = response.statusCode();
        String headerLocation = response.headers().getValue("Location");
        //String headerLocationId = headerLocation.split("/")[4];

        assertThat(responseStatus).isEqualTo(201);
        //assertThat(headerLocation).isEqualTo("http://localhost:8080/products" + headerLocationId);
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
    void whenMakingGETResquestToProductEndpoint_thenCorrect(){
        //TODO 200
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
