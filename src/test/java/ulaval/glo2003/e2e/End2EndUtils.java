package ulaval.glo2003.e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.UI.SellerRequest;

import static io.restassured.RestAssured.*;

public class End2EndUtils {

    private static final String SELLER_HEADER_NAME = "X-Seller-Id";
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
        Response response = createResource("/sellers", sellerRequest);
        return extractLocationId(response);
    }


    public static String addProductToSellerGetId(String sellerId){
       Response productAdded = createProductResource(createValidProduct(), sellerId);
       return extractLocationId(productAdded);
    }

    public static Response createSellerResource(SellerRequest sellerRequest){
        return createResource("/sellers", sellerRequest);
    }

    public static Response getSellerById(String sellerId) throws JsonProcessingException {
        Response sellerResponse = getResourceById("/sellers/{sellerId}", sellerId);
        return sellerResponse;
}

    public static ProductRequest createValidProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = "My Product";
        productRequest.description = "Best product in the world";
        productRequest.suggestedPrice = 5.0;
        productRequest.categories = new ArrayList<>(List.of("beauty"));
        return productRequest;
    }

    public static Response createProductResource(ProductRequest productRequest, String sellerId){
        Header productSeller = new Header(SELLER_HEADER_NAME, sellerId);
        return createResource("/products", productSeller, productRequest);
    }

    public static String createProductGetId(){
        ProductRequest productRequest = createValidProduct();
        Response response = createProductResource(productRequest, createSellerGetId());
        return extractLocationId(response);
    }

    public static Response getProductById(String productId) throws JsonProcessingException {
        Response productResponse = getResourceById("/products/{productId}", productId);

        return productResponse;
    }

    public static Response createResource(String path, Object bodyPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }
    private static Response createResource(String path, Header header, Object bodyPayload) {
        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    private static Response getResourceById(String pathParam, String resourceId) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(pathParam, resourceId)
                .then()
                .extract()
                .response();
    }

    public static String extractLocationId(Response response){
        String location = response.path("id");
        return location.substring(location.lastIndexOf("/") + 1);
    }

    }

