package ulaval.glo2003.e2e;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.UI.SellerRequest;

import static io.restassured.RestAssured.*;

public class End2EndUtils {

    private static final String SELLER_HEADER_NAME = "X-Seller-Id";

    public static int getHealth(){
        return given()
                .log().all()
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
        Response response = createProductResource(createValidProduct(), sellerId);
        return extractLocationId(response);
    }

    public static Response createSellerResource(SellerRequest sellerRequest){
        return createResource("/sellers", sellerRequest);
    }

    public static Response getSellerById(String sellerId) {
        return getResourceById("/sellers/{sellerId}", sellerId);
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

    public static Response getProductById(String productId) {
        return getResourceById("/products/{productId}", productId);
    }

    public static Response createResource(String path, Object request) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(request)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }
    private static Response createResource(String path, Header header, Object request) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .header(header)
                .body(request)
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
        String location = response.header("Location");
        return location.substring(location.lastIndexOf("/") + 1);
    }

    }

