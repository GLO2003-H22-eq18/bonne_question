package ulaval.glo2003.e2e;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class End2EndUtils {

    private static final String SELLER_HEADER_NAME = "X-Seller-Id";

    public static int getHealth(){
        return given().contentType(ContentType.JSON)
                .when()
                .get("/health")
                .then()
                .extract().statusCode();
    }

    public static SellerRequest createValidSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = "1977-04-23";
        return sellerRequest;
    }

    public static SellerRequest createMissingParamSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "John Cena";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = null;
        return sellerRequest;
    }

    public static SellerRequest createInvalidParamSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = "    \n  \t \n ";
        sellerRequest.bio = "What a chad!";
        sellerRequest.birthDate = "1977-04-23";
        return sellerRequest;
    }

    public static String createSellerGetId(){
        SellerRequest sellerRequest = createValidSeller();
        ExtractableResponse<Response> response = createResource("/sellers", sellerRequest);
        return response.header("Location").split("/")[4];
    }

    public static ExtractableResponse<Response> createSellerResource(SellerRequest sellerRequest){
        return createResource("/sellers", sellerRequest);
    }

    public static ProductRequest createProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = "My Product";
        productRequest.description = "Best product in the world";
        productRequest.suggestedPrice = 5.0;
        productRequest.categories = new ArrayList<>(List.of("beauty"));
        return productRequest;
    }

    public static ExtractableResponse<Response> createProductResource(ProductRequest productRequest, String sellerId){
        Header productSeller = new Header(SELLER_HEADER_NAME, sellerId);
        return createResource("/products", productSeller, productRequest);
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
    private static ExtractableResponse<Response> createResource(String path, Header header, Object bodyPayload) {
        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .extract();
    }

    private static ExtractableResponse<Response> getResource(String locationHeader) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(locationHeader)
                .then()
                .extract();
    }

    public static String extractLocationId(ExtractableResponse<Response> response){
        String location = response.header("Location");
        return location.substring(location.lastIndexOf("/") + 1);
    }
    }

