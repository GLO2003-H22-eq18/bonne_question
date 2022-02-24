package ulaval.glo2003.e2e;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class End2EndUtils {

    public static int getHealth(){
        return given().contentType(ContentType.JSON)
                .when()
                .get("/health")
                .then()
                .extract().statusCode();
    }

    public static SellerRequest createSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = "1977-04-23";
        return sellerRequest;
    }
    public static ProductRequest createProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = "My Product";
        productRequest.description = "Best product in the world";
        productRequest.suggestedPrice = 5.0;
        productRequest.categories = new ArrayList<>(List.of("beauty"));
        return productRequest;
    }

    public static String createSellerGetId(){
        SellerRequest sellerRequest = createSeller();
        ExtractableResponse<Response> response = createResource("/sellers", sellerRequest);
        return response.header("Location").split("/")[4];
    }

    public static ExtractableResponse<Response> createResource(String path, Object bodyPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .extract();
    }
    public static ExtractableResponse<Response> createResource(String path, Header header, Object bodyPayload) {
        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> getResource(String locationHeader) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(locationHeader)
                .then()
                .extract();
    }
    }

