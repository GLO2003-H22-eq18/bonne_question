package ulaval.glo2003.e2e;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import ulaval.glo2003.Exceptions.ErrorCode;
import ulaval.glo2003.Exceptions.ErrorResponse;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.UI.SellerProductResponse;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResponse;
import ulaval.glo2003.subjects.OffsetDateTimeSubject;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.*;

public class End2EndUtils {
    public static final String A_INVALID_ID = "13200298A";
    public static final String SELLER_HEADER_NAME = "X-Seller-Id";
    public static final String A_VALID_SELLER_NAME = "John Cena";
    public static final String A_VALID_SELLER_BIO = "What a chad!";
    public static final String A_VALID_SELLER_BIRTHDATE = "1997-04-23";
    public static final String A_INVALID_SELLER_NAME = "    \n  \t \n ";
    public static final String A_INVALID_SELLER_BIO = "    \n  \t \n ";
    public static final String A_INVALID_SELLER_BIRTHDATE = "2100-11-01";
    public static final String A_VALID_PRODUCT_TITLE = "Foo Bar";
    public static final String A_VALID_PRODUCT_DESCRIPTION = "An awesome generic product!";
    public static final Double A_VALID_PRODUCT_SUGGESTED_PRICE = 5.0;
    public static final List<String> A_VALID_PRODUCT_CATEGORIES = new ArrayList(List.of("beauty", "electronics"));
    public static final String A_INVALID_PRODUCT_TITLE = "    \n  \t \n ";
    public static final String A_INVALID_PRODUCT_DESCRIPTION = "    \n  \t \n ";
    public static final Double A_INVALID_PRODUCT_SUGGESTED_PRICE = 0.99;
    public static final List<String> A_INVALID_PRODUCT_CATEGORIES = new ArrayList(List.of("BeAuTy", "JavaBaby", "electronics"));

    public static int getHealth(){
        return given()
                .when()
                .get("/health")
                .then()
                .extract().statusCode();
    }

    public static String extractLocationId(Response response){
        String location = response.header("Location");
        return location.substring(location.lastIndexOf("/") + 1);
    }

    public static SellerRequest createValidSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = A_VALID_SELLER_NAME;
        sellerRequest.bio = A_VALID_SELLER_BIO;
        sellerRequest.birthDate = A_VALID_SELLER_BIRTHDATE;
        return sellerRequest;
    }

    public static SellerRequest createSellerWithMissingParams() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = null;
        sellerRequest.bio = null;
        sellerRequest.birthDate = null;
        return sellerRequest;
    }

    public static SellerRequest createSellerWithInvalidName() {
        SellerRequest sellerRequest = createValidSeller();
        sellerRequest.name = A_INVALID_SELLER_NAME;
        return sellerRequest;
    }
    public static SellerRequest createSellerWithInvalidBio() {
        SellerRequest sellerRequest = createValidSeller();
        sellerRequest.bio = A_INVALID_SELLER_BIO;
        return sellerRequest;
    }

    public static SellerRequest createSellerWithInvalidAge() {
        SellerRequest sellerRequest = createValidSeller();
        sellerRequest.birthDate = A_INVALID_SELLER_BIRTHDATE;
        return sellerRequest;
    }

    public static String createValidSellerGetId(){
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
        productRequest.title = A_VALID_PRODUCT_TITLE;
        productRequest.description = A_VALID_PRODUCT_DESCRIPTION;
        productRequest.suggestedPrice = A_VALID_PRODUCT_SUGGESTED_PRICE;
        productRequest.categories = A_VALID_PRODUCT_CATEGORIES;

        return productRequest;
    }

    public static ProductRequest createValidProductWithoutCategories() {
        ProductRequest productRequest = createValidProduct();
        productRequest.categories = A_VALID_PRODUCT_CATEGORIES;
        return productRequest;
    }

    public static ProductRequest createProductWithMissingParams() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = null;
        productRequest.description = null;
        productRequest.suggestedPrice = null;
        productRequest.categories = null;
        return productRequest;
    }

    public static ProductRequest createProductWithInvalidTitle() {
        ProductRequest productRequest = createValidProduct();
        productRequest.title = A_INVALID_PRODUCT_TITLE;
        return productRequest;
    }

    public static ProductRequest createProductWithInvalidDescription() {
        ProductRequest productRequest = createValidProduct();
        productRequest.description = A_INVALID_PRODUCT_DESCRIPTION;
        return productRequest;
    }
    public static ProductRequest createProductWithInvalidPrice() {
        ProductRequest productRequest = createValidProduct();
        productRequest.suggestedPrice = A_INVALID_PRODUCT_SUGGESTED_PRICE;
        return productRequest;
    }

    public static ProductRequest createProductWithInvalidCategories() {
        ProductRequest productRequest = createValidProduct();
        productRequest.categories = A_INVALID_PRODUCT_CATEGORIES;
        return productRequest;
    }

    public static Response createProductResource(ProductRequest productRequest, String sellerId){
        Headers productSeller = new Headers(new Header(SELLER_HEADER_NAME, sellerId));
        return createResource("/products", productRequest, productSeller);
    }

    public static String createValidProductGetId(String sellerId){
        ProductRequest productRequest = createValidProduct();
        Response response = createProductResource(productRequest, sellerId);
        return extractLocationId(response);
    }

    public static Response getProductById(String productId) {
        return getResourceById("/products/{productId}", productId);
    }

    public static Response getProductsBySellerId(String sellerId) {
        Map<String, String> filter = new HashMap<>(Map.of("", ""));
        return null;//getResourceByFilter();
    }

    public static Response getProductsByTitle(String title) {
        Map<String, String> filter = new HashMap<>(Map.of("", ""));
        return null;//getResourceByFilter();
    }

    public static Response getProductsByCategories(List<String> categories){
        Map<String, String> filter = new HashMap<>(Map.of("", ""));
        return null;//getResourceByFilter();
    }

    public static Response getProductsByMinPrice(Double minPrice){
        Map<String, String> filter = new HashMap<>(Map.of("", ""));
        return null;//getResourceByFilter();
    }

    public static Response getProductsByMaxPrice(Double maxPrice){
        Map<String, String> filter = new HashMap<>(Map.of("", ""));
        return null;//getResourceByFilter();
    }

    public static void assertThatPostResponseIsValid(Response postResponse){
        assertThat(postResponse.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        assertThat(isNullOrEmpty(extractLocationId(postResponse))).isFalse();
        assertThat(isNullOrEmpty(postResponse.body().asString())).isTrue();
    }

    public static void assertThatSellerResponseFieldsAreValid(SellerResponse sellerResponse, String expectedSellerId, String expectedProductId){
        assertThat(sellerResponse.id).isEqualTo(expectedSellerId);
        assertThat(sellerResponse.name).isEqualTo(A_VALID_SELLER_NAME);
        assertThat(sellerResponse.bio).isEqualTo(A_VALID_SELLER_BIO);
        OffsetDateTimeSubject.assertThat(sellerResponse.createdAt).isWithinExpectedRange();

        SellerProductResponse sellerProductResponse = sellerResponse.products.get(0);
        assertThatSellerProductResponseFieldsAreValid(sellerProductResponse, expectedProductId);
    }

    public static void assertThatSellerProductResponseFieldsAreValid(SellerProductResponse productResponse, String expectedProductId){
        assertThat(productResponse.id).isEqualTo(expectedProductId);
        OffsetDateTimeSubject.assertThat(productResponse.createdAt).isWithinExpectedRange();
        assertThat(productResponse.title).isEqualTo (A_VALID_PRODUCT_TITLE);
        assertThat(productResponse.description).isEqualTo (A_VALID_PRODUCT_DESCRIPTION);
        assertThat(productResponse.suggestedPrice).isEqualTo (A_VALID_PRODUCT_SUGGESTED_PRICE);
        assertThat(productResponse.categories).isEqualTo(A_VALID_PRODUCT_CATEGORIES);
        assertThat(productResponse.offers.count).isEqualTo(0);
    }

    public static void assertThatProductResponseFieldsAreValid(ProductResponse productResponse, String expectedProductId,
                                                                     String expectedSellerId){
        assertThat(productResponse.seller.id).isEqualTo(expectedSellerId);
        assertThat(productResponse.seller.name).isEqualTo(A_VALID_SELLER_NAME);
        assertThat(productResponse.id).isEqualTo(expectedProductId);
        OffsetDateTimeSubject.assertThat(productResponse.createdAt).isWithinExpectedRange();
        assertThat(productResponse.title).isEqualTo (A_VALID_PRODUCT_TITLE);
        assertThat(productResponse.description).isEqualTo (A_VALID_PRODUCT_DESCRIPTION);
        assertThat(productResponse.suggestedPrice).isEqualTo (A_VALID_PRODUCT_SUGGESTED_PRICE);
        assertThat(productResponse.categories).isEqualTo(A_VALID_PRODUCT_CATEGORIES);
        assertThat(productResponse.offers.count).isEqualTo(0);
    }


    public static void assertThatResponseIsItemNotFoundError(Response response){
        assertThatErrorResponseIsValid(response, HttpStatus.SC_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND);
    }

    public static void assertThatResponseIsMissingParamError(Response response){
        assertThatErrorResponseIsValid(response, HttpStatus.SC_BAD_REQUEST, ErrorCode.MISSING_PARAMETER);
    }

    public static void assertThatResponseIsInvalidParamError(Response response){
        assertThatErrorResponseIsValid(response, HttpStatus.SC_BAD_REQUEST, ErrorCode.INVALID_PARAMETER);
    }

    private static void assertThatErrorResponseIsValid(Response response, int expectedStatus, ErrorCode expectedCode){
        assertThat(response.statusCode()).isEqualTo(expectedStatus);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertThat(errorResponse.description).isNotNull();
        assertThat(errorResponse.code).isEqualTo(expectedCode);
    }

    private static Response createResource(String path, Object request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    private static Response createResource(String path, Object request, Headers header) {
        return given()
                .contentType(ContentType.JSON)
                .headers(header)
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

    private static Response getResourceByFilter(String path, Map<String, String> filters) {
        return given()
                .queryParams(filters)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }
    }

