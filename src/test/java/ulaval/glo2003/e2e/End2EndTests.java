package ulaval.glo2003.e2e;


import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import ulaval.glo2003.Exceptions.ErrorResponse;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.ProductRequest;

import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResponse;

import static com.google.common.base.Strings.isNullOrEmpty;
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
    void givenValidRequest_whenCreatingSeller_thenSellerCreatedWithStatus201() {
        SellerRequest sellerRequest = createValidSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        assertThat(isNullOrEmpty(extractLocationId(response))).isFalse();
    }

    @Test
    void givenValidRequest_whenCreatingProduct_thenProductCreatedWithStatus201() {
        ProductRequest productRequest = createValidProduct();

        Response response = createProductResource(productRequest, createSellerGetId());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        assertThat(isNullOrEmpty(extractLocationId(response))).isFalse();
    }

    @Test
    void givenProductId_whenGettingProduct_thenProductReturned() {
        String productId = createProductGetId(); //POST à sellers + POST à products, retourne id

        Response response = getProductById(productId);
        System.out.println(response.body().asPrettyString());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        ProductResponse productResponse = response.body().as(ProductResponse.class);
        assertThat(productResponse.categories.get(0)).isEqualTo("beauty");
    }

    @Test
    void givenSellerId_whenGettingSeller_thenSellerReturnedWithProduct() {
        String sellerId = createSellerGetId();
        String productId = addProductToSellerGetId(sellerId);

        Response response = getSellerById(sellerId);
        System.out.println(response.body().asPrettyString());

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        SellerResponse sellerResponse = response.as(SellerResponse.class);
        assertThat(sellerResponse).isNotNull();
    }

    @Test
    void givenSellerRequestWithMissingParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createMissingParamSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
    }

    @Test
    void givenSellerRequestWithInvalidParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createInvalidParamSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
    }



}