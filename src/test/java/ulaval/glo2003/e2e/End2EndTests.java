package ulaval.glo2003.e2e;


import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.ProductRequest;

import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResponse;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.*;


class End2EndTests {

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[0]);

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
    void givenSellerRequestWithMissingParam_whenCreatingSeller_thenReturnsError400() {
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
    void givenSellerRequestWithInvalidBirthDate_whenCreatingSeller_thenReturnsError400() {
        SellerRequest sellerRequest = createSellerWithInvalidAge();

        Response response = createSellerResource(sellerRequest);

        assertThatResponseIsInvalidParamError(response);
    }

    @Test
    void givenValidSellerIdWithProduct_whenGettingSeller_thenReturnsSellerWithProductAndStatus200() {
        String sellerId = createValidSellerGetId();
        String productId = addProductToSellerGetId(sellerId);

        Response response = getSellerById(sellerId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        SellerResponse sellerResponse = response.as(SellerResponse.class);
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

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        ProductResponse productResponse = response.body().as(ProductResponse.class);
        assertThatProductResponseFieldsAreValid(productResponse, productId, sellerId);
    }

    @Test
    void givenInvalidProductId_whenGettingProduct_thenReturnsError404() {
        Response response = getProductById(A_INVALID_ID);

        assertThatResponseIsItemNotFoundError(response);
    }





}