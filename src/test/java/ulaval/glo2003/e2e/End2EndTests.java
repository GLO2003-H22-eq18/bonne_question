package ulaval.glo2003.e2e;


import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.A_INVALID_ID;
import static ulaval.glo2003.e2e.End2EndUtils.A_RANDOM_VALID_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.End2EndUtils.A_VALID_PRODUCT_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.End2EndUtils.NUMBER_OF_PRODUCTS;
import static ulaval.glo2003.e2e.End2EndUtils.VALID_PRODUCT_CATEGORIES;
import static ulaval.glo2003.e2e.End2EndUtils.addProductToSellerGetId;
import static ulaval.glo2003.e2e.End2EndUtils.addRandomProductsToSeller;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatAllProductsHaveAtLeastOneCategoryFromGivenCategories;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatAllProductsHaveTheSameSellerId;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatAllProductsPriceIsGreaterOrEqualToMinPrice;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatAllProductsPriceIsLesserOrEqualToMaxPrice;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatPostResponseIsValid;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatProductResponseFieldsAreValid;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsInvalidParamError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsItemNotFoundError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsMissingParamError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatSellerResponseFieldsAreValid;
import static ulaval.glo2003.e2e.End2EndUtils.createProductResource;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidCategories;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidDescription;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidTitle;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithMissingParams;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithMaxPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithMinPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithTitle;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsWithCommonCategoriesFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsWithoutCategoriesFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createSellerResource;
import static ulaval.glo2003.e2e.End2EndUtils.createSellerWithInvalidAge;
import static ulaval.glo2003.e2e.End2EndUtils.createSellerWithInvalidBio;
import static ulaval.glo2003.e2e.End2EndUtils.createSellerWithInvalidName;
import static ulaval.glo2003.e2e.End2EndUtils.createSellerWithMissingParams;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProduct;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProductGetId;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProductWithoutCategories;
import static ulaval.glo2003.e2e.End2EndUtils.createValidSeller;
import static ulaval.glo2003.e2e.End2EndUtils.createValidSellerGetId;
import static ulaval.glo2003.e2e.End2EndUtils.getHealth;
import static ulaval.glo2003.e2e.End2EndUtils.getProductById;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByCategories;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByMaxPrice;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByMinPrice;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsBySellerId;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByTitle;
import static ulaval.glo2003.e2e.End2EndUtils.getSellerById;

import io.restassured.response.Response;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.FilteredProductsResponse;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResponse;


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
    void givenSellerWithProducts_whenFilteringProductsBySellerId_thenProductsFromSpecifiedSellerReturnedWithStatus200() {
        String sellerId = createValidSellerGetId();
        addRandomProductsToSeller(sellerId, NUMBER_OF_PRODUCTS);

        Response response = getProductsBySellerId(sellerId);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
        assertThatAllProductsHaveTheSameSellerId(filteredProductsResponse.products, sellerId);
    }

    @Test
    void givenSellerWithNoProducts_whenFilteringProductsBySellerId_thenNoProductsReturnedWithStatus200() {
        String sellerWithoutProductsId = createValidSellerGetId();
        createRandomProductsFromRandomSellers(NUMBER_OF_PRODUCTS);

        Response response = getProductsBySellerId(sellerWithoutProductsId);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(0);
    }

    @Test
    void givenProductsWithCommonTitle_whenFilteringProductsByTitle_thenProductsWithTitleIncludedReturnedWithStatus200() {
        String title = A_RANDOM_VALID_PRODUCT_TITLE;
        createRandomProductsFromRandomSellersWithTitle(title, NUMBER_OF_PRODUCTS);

        Response response = getProductsByTitle(title);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
    }

    @Test
    void givenProductsWithCommonCategories_whenFilteringProductsByCategory_thenProductsWithCategoryIncludedReturnedWithStatus200() {
        createRandomProductsWithCommonCategoriesFromRandomSellers(VALID_PRODUCT_CATEGORIES,
                NUMBER_OF_PRODUCTS);

        Response response = getProductsByCategories(VALID_PRODUCT_CATEGORIES);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
        assertThatAllProductsHaveAtLeastOneCategoryFromGivenCategories(
                filteredProductsResponse.products, VALID_PRODUCT_CATEGORIES);
    }

    @Test
    void givenProductsWithoutCategories_whenFilteringProductsByCategory_thenNoProductsReturnedWithStatus200() {
        createRandomProductsWithoutCategoriesFromRandomSellers(NUMBER_OF_PRODUCTS);

        Response response = getProductsByCategories(VALID_PRODUCT_CATEGORIES);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products).isEmpty();
    }

    @Test
    void givenProductsWithPriceGreaterOrEqualToMinPrice_whenFilteringProductsByMinPrice_thenAllProductsReturnedWithStatus200() {
        createRandomProductsFromRandomSellersWithMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE,
                NUMBER_OF_PRODUCTS);

        Response response = getProductsByMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
        assertThatAllProductsPriceIsGreaterOrEqualToMinPrice(
                filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
    }

    @Test
    void givenProductsWithPriceLesserThanMinPrice_whenFilteringProductsByMinPrice_thenNoProductsReturnedWithStatus200() {
        createRandomProductsFromRandomSellersWithMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE,
                NUMBER_OF_PRODUCTS);

        Response response = getProductsByMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products).isEmpty();
    }

    @Test
    void givenProductsWithPriceLesserOrEqualToMaxPrice_whenFilteringProductsByMaxPrice_thenAllProductsReturnedWithStatus200() {
        createRandomProductsFromRandomSellersWithMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE,
                NUMBER_OF_PRODUCTS);

        Response response = getProductsByMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products.size()).isEqualTo(NUMBER_OF_PRODUCTS);
        assertThatAllProductsPriceIsLesserOrEqualToMaxPrice(
                filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
    }

    @Test
    void givenProductsWithPriceGreaterThanMaxPrice_whenFilteringProductsByMaxPrice_thenNoProductsReturnedWithStatus200() {
        createRandomProductsFromRandomSellersWithMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE,
                NUMBER_OF_PRODUCTS);

        Response response = getProductsByMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
        FilteredProductsResponse filteredProductsResponse =
                response.as(FilteredProductsResponse.class);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(filteredProductsResponse.products).isEmpty();
    }
}