package ulaval.glo2003.e2e;


import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.FilteredProductsResponse;
import ulaval.glo2003.Product.UI.ProductRequest;

import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResponse;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.*;


class End2EndTests {

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

    @Test
    void healthReturnsOk() {
        int responseStatus = getHealth();

        assertThat(responseStatus).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void givenValidSellerRequest_whenCreatingSeller_thenSellerCreatedWithStatus201() {
        SellerRequest sellerRequest = createValidSeller();

        Response response = createSellerResource(sellerRequest);

        assertThatPostResponseIsValid(response);
    }

    @Test
    void givenSellerRequestWithMissingParams_whenCreatingSeller_thenReturnsError400() {
        SellerRequest sellerRequest = createSellerWithMissingParams();

        Response response = createSellerResource(sellerRequest);

        assertThatResponseIsMissingParamError(response);
    }

    @Test
    void givenSellerRequestWithInvalidName_whenCreatingSeller_thenReturnsError400() {
        SellerRequest sellerRequest = createSellerWithInvalidName();

        Response response = createSellerResource(sellerRequest);

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenSellerRequestWithInvalidBio_whenCreatingSeller_thenReturnsError400() {
        SellerRequest sellerRequest = createSellerWithInvalidBio();

        Response response = createSellerResource(sellerRequest);

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenSellerRequestWithInvalidAge_whenCreatingSeller_thenReturnsError400() {
        SellerRequest sellerRequest = createSellerWithInvalidAge();

        Response response = createSellerResource(sellerRequest);

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenValidSellerIdWithProduct_whenGettingSeller_thenReturnsSellerWithProductAndStatus200() {
        String sellerId = createValidSellerGetId();
        String productId = addProductToSellerGetId(sellerId);

        Response response = getSellerById(sellerId);
        SellerResponse sellerResponse = response.as(SellerResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThatSellerResponseFieldsAreValid(sellerResponse, sellerId, productId);
    }

    @Test
    void givenInvalidSellerId_whenGettingSeller_thenReturnsError404() {
        Response response = getSellerById(A_INVALID_ID);

        assertThatResponseIsItemNotFoundError(response);
    }

    @Test
    void givenValidProductRequest_whenCreatingProduct_thenProductCreatedWithStatus201() {
        ProductRequest productRequest = createValidProduct();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatPostResponseIsValid(response);
    }

    @Test
    void givenValidProductRequestWithoutCategories_whenCreatingProduct_thenProductCreatedWithStatus201() {
        ProductRequest productRequest = createValidProductWithoutCategories();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatPostResponseIsValid(response);
    }

    @Test
    void givenProductRequestWithMissingParam_whenCreatingProduct_thenReturnsError400() {
        ProductRequest productRequest = createProductWithMissingParams();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatResponseIsMissingParamError(response);
    }

    @Test
    void givenProductRequestWithInvalidTitle_whenCreatingProduct_thenReturnsError400() {
        ProductRequest productRequest = createProductWithInvalidTitle();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenProductRequestWithInvalidDescription_whenCreatingProduct_thenReturnsError400() {
        ProductRequest productRequest = createProductWithInvalidDescription();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenProductRequestWithInvalidPrice_whenCreatingProduct_thenReturnsError400() {
        ProductRequest productRequest = createProductWithInvalidPrice();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenProductRequestWithInvalidCategories_whenCreatingProduct_thenReturnsError400() {
        ProductRequest productRequest = createProductWithInvalidCategories();

        Response response = createProductResource(productRequest, createValidSellerGetId());

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenValidProductId_whenGettingProduct_thenProductReturnedWithStatus200() {
        String sellerId = createValidSellerGetId();
        String productId = createValidProductGetId(sellerId);

        Response response = getProductById(productId);
        ProductResponse productResponse = response.body().as(ProductResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThatProductResponseFieldsAreValid(productResponse, productId, sellerId);
    }

    @Test
    void givenInvalidProductId_whenGettingProduct_thenReturnsError404() {
        Response response = getProductById(A_INVALID_ID);

        assertThatResponseIsItemNotFoundError(response);
    }

    @Test
    void givenSellerWithProducts_whenFilteringProductsBySellerId_thenProductsFromSpecifiedSellerReturnedWithStatus200(){
        String sellerId = createValidSellerGetId();
        addRandomProductsToSeller(sellerId, NUMBER_OF_PRODUCTS);

        Response response = getProductsBySellerId(sellerId);
        FilteredProductsResponse filteredProductsResponse = response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
        assertThatAllProductsHaveTheSameSellerId(filteredProductsResponse.products, sellerId);
    }

    @Test
    void givenSellerWithNoProducts_whenFilteringProductsBySellerId_thenNoProductsReturnedWithStatus200(){
        String sellerWithoutProductsId = createValidSellerGetId();
        createRandomProductsFromRandomSellers(NUMBER_OF_PRODUCTS);

        Response response = getProductsBySellerId(sellerWithoutProductsId);
        FilteredProductsResponse filteredProductsResponse = response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(0);
    }

    @Test
    void givenProductsWithCommonTitle_whenFilteringProductsByTitle_thenProductsWithTitleIncludedReturnedWithStatus200(){
        String title = A_RANDOM_VALID_PRODUCT_TITLE;
        createRandomProductsFromRandomSellersWithTitle(title, NUMBER_OF_PRODUCTS);

        Response response = getProductsByTitle(title);
        FilteredProductsResponse filteredProductsResponse = response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
    }

    @Test
    void givenProductsWithCommonCategories_whenFilteringProductsByCategory_thenProductsWithCategoryIncludedReturnedWithStatus200(){
        createRandomProductsFromRandomSellersWithCategories(VALID_PRODUCT_CATEGORIES, NUMBER_OF_PRODUCTS);

        Response response = getProductsByCategories(VALID_PRODUCT_CATEGORIES);
        FilteredProductsResponse filteredProductsResponse = response.as(FilteredProductsResponse.class);
        System.out.println(response.asPrettyString());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
    }

    @Test
    void givenProductsWithCategories_whenFilteringProductsByEmptyCategory_thenNoProductsReturnedWithStatus200(){
        createRandomProductsFromRandomSellersWithCategories(VALID_PRODUCT_CATEGORIES, NUMBER_OF_PRODUCTS);

        Response response = getProductsByCategories(VALID_PRODUCT_CATEGORIES);
        FilteredProductsResponse filteredProductsResponse = response.as(FilteredProductsResponse.class);
        System.out.println(response.asPrettyString());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
    }

    @Test
    void whenFilteringProductsByMinPrice_thenFilteredProductsReturnedWithStatus200(){

    }
    @Test
    void whenFilteringProductsByMaxPrice_thenFilteredProductsReturnedWithStatus200(){

    }





}