package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.ALL_PRODUCT_CATEGORIES;
import static ulaval.glo2003.e2e.End2EndUtils.A_INVALID_ID;
import static ulaval.glo2003.e2e.End2EndUtils.A_RANDOM_VALID_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.End2EndUtils.A_VALID_PRODUCT_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.End2EndUtils.NUMBER_OF_PRODUCTS;
import static ulaval.glo2003.e2e.End2EndUtils.VALID_PRODUCT_CATEGORIES;
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
import static ulaval.glo2003.e2e.End2EndUtils.createProductResource;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidCategories;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidDescription;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithInvalidTitle;
import static ulaval.glo2003.e2e.End2EndUtils.createProductWithMissingParams;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductGetResponse;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithMaxPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithMinPrice;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsFromRandomSellersWithTitle;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsWithCommonCategoriesFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createRandomProductsWithoutCategoriesFromRandomSellers;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProduct;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProductGetId;
import static ulaval.glo2003.e2e.End2EndUtils.createValidProductWithoutCategories;
import static ulaval.glo2003.e2e.End2EndUtils.createValidSellerGetId;
import static ulaval.glo2003.e2e.End2EndUtils.getCorrespondingFilters;
import static ulaval.glo2003.e2e.End2EndUtils.getProductById;
import static ulaval.glo2003.e2e.End2EndUtils.getProducts;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByCategories;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByMaxPrice;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByMinPrice;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsBySellerId;
import static ulaval.glo2003.e2e.End2EndUtils.getProductsByTitle;

import io.restassured.response.Response;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpStatus;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.FilteredProductsResponse;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Product.UI.ProductResponse;

@DisplayName("Product Resource")
public class ProductResourceTest {

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

    @DisplayName("WHEN creating product")
    @Nested
    class WhenCreatingProduct {

        @DisplayName("GIVEN all valid fields THEN returns status 201 created")
        @Test
        void givenValidProductRequest_whenCreatingProduct_thenProductCreatedWithStatus201() {
            ProductRequest productRequest = createValidProduct();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatPostResponseIsValid(response);
        }

        @DisplayName("GIVEN no optional parameters THEN returns status 201 created")
        @Test
        void givenValidProductRequestWithoutCategories_whenCreatingProduct_thenProductCreatedWithStatus201() {
            ProductRequest productRequest = createValidProductWithoutCategories();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatPostResponseIsValid(response);
        }

        @DisplayName("GIVEN no required parameters THEN returns error 400 bad request")
        @Test
        void givenProductRequestWithMissingParam_whenCreatingProduct_thenReturnsError400() {
            ProductRequest productRequest = createProductWithMissingParams();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatResponseIsMissingParamError(response);
        }

        @DisplayName("GIVEN invalid title THEN returns error 400 bad request")
        @Test
        void givenProductRequestWithInvalidTitle_whenCreatingProduct_thenReturnsError400() {
            ProductRequest productRequest = createProductWithInvalidTitle();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatResponseIsInvalidParamError(response);
        }

        @DisplayName("GIVEN invalid description THEN returns error 400 bad request")
        @Test
        void givenProductRequestWithInvalidDescription_whenCreatingProduct_thenReturnsError400() {
            ProductRequest productRequest = createProductWithInvalidDescription();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatResponseIsInvalidParamError(response);
        }

        @DisplayName("GIVEN invalid price THEN returns error 400 bad request")
        @Test
        void givenProductRequestWithInvalidPrice_whenCreatingProduct_thenReturnsError400() {
            ProductRequest productRequest = createProductWithInvalidPrice();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatResponseIsInvalidParamError(response);
        }

        @DisplayName("GIVEN invalid categories THEN returns error 400 bad request")
        @Test
        void givenProductRequestWithInvalidCategories_whenCreatingProduct_thenReturnsError400() {
            ProductRequest productRequest = createProductWithInvalidCategories();

            Response response = createProductResource(productRequest, createValidSellerGetId());

            assertThatResponseIsInvalidParamError(response);
        }
    }

    @DisplayName("WHEN getting product")
    @Nested
    class WhenGettingProduct {

        @DisplayName("GIVEN valid id THEN returns product and status 200 ok")
        @Test
        void givenValidProductId_whenGettingProduct_thenProductReturnedWithStatus200() {
            String sellerId = createValidSellerGetId();
            String productId = createValidProductGetId(sellerId);

            Response response = getProductById(productId);
            ProductResponse productResponse = response.body().as(ProductResponse.class);

            assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThatProductResponseFieldsAreValid(productResponse, productId, sellerId);
        }

        @DisplayName("GIVEN invalid id THEN returns error 404 not found")
        @Test
        void givenInvalidProductId_whenGettingProduct_thenReturnsError404() {
            Response response = getProductById(A_INVALID_ID);

            assertThatResponseIsItemNotFoundError(response);
        }

        @DisplayName("WHEN filtering")
        @Nested
        class WhenFilteringProduct {

            @BeforeEach
            public void test() {
                System.out.println("hello world");
            }

            @DisplayName("GIVEN all filters THEN returns products that include specified filters " +
                    "and status 200 ok")
            @Test
            void givenProductThatMatchesAllFilters_whenFilteringProducts_thenReturnsTheProductThatMatchGivenFiltersWithStatus200() {
                ProductResponse expectedProduct = createRandomProductGetResponse();

                Response response = getProducts(getCorrespondingFilters(expectedProduct));
                ProductResponse actualProduct =
                        response.as(FilteredProductsResponse.class).products.get(0);

                assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                assertThat(actualProduct.id).isEqualTo(expectedProduct.id);
            }


            @DisplayName("GIVEN no filters THEN returns all products from all sellers and status " +
                    "200 ok")
            @Test
            void givenRandomProducts_whenFilteringProductsWithoutFilter_thenAllProductsFromAllSellersReturnedWithStatus200() {
                createRandomProductsFromRandomSellers(NUMBER_OF_PRODUCTS);

                Response response = getProducts();
                FilteredProductsResponse filteredProductsResponse =
                        response.as(FilteredProductsResponse.class);

                assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                assertThat(filteredProductsResponse.products).isNotEmpty();
            }

            @DisplayName("BY Seller ID")
            @Nested
            class BySellerId {

                @DisplayName(
                        "GIVEN a seller with products THEN returns all products from specified " +
                                "seller and status 200 ok")
                @Test
                void givenSellerWithProducts_whenFilteringProductsBySellerId_thenProductsFromSpecifiedSellerReturnedWithStatus200() {
                    String sellerId = createValidSellerGetId();
                    addRandomProductsToSeller(sellerId, NUMBER_OF_PRODUCTS);

                    Response response = getProductsBySellerId(sellerId);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThat(filteredProductsResponse.products.size()).isEqualTo(
                            NUMBER_OF_PRODUCTS);
                    assertThatAllProductsHaveTheSameSellerId(filteredProductsResponse.products,
                            sellerId);
                }

                @DisplayName(
                        "GIVEN a seller without products THEN returns no product with status " +
                                "200 ok")
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

                @DisplayName("GIVEN invalid id THEN returns no product with status 200 ok")
                @Test
                void givenInvalidSellerId_whenFilteringProductsBySellerId_thenReturnsError400() {
                    String sellerId = A_INVALID_ID;

                    Response response = getProductsBySellerId(A_INVALID_ID);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThat(filteredProductsResponse.products.size()).isEqualTo(0);

                }
            }

            @DisplayName("By Title")
            @Nested
            class ByTitle {

                @DisplayName(
                        "GIVEN products with title THEN returns products with title included and " +
                                "status 200 ok")
                @Test
                void givenProductsWithCommonTitle_whenFilteringProductsByTitle_thenProductsWithTitleIncludedReturnedWithStatus200() {
                    String title = A_RANDOM_VALID_PRODUCT_TITLE;
                    createRandomProductsFromRandomSellersWithTitle(title, NUMBER_OF_PRODUCTS);

                    Response response = getProductsByTitle(title);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThat(filteredProductsResponse.products.size()).isEqualTo(
                            NUMBER_OF_PRODUCTS);
                }
            }

            @DisplayName("By Categories")
            @Nested
            class ByCategories {

                @DisplayName(
                        "GIVEN products with categories THEN returns products with at least one " +
                                "category included and status 200 ok")
                @Test
                void givenProductsWithCommonCategories_whenFilteringProductsByCategory_thenProductsWithCategoryIncludedReturnedWithStatus200() {
                    createRandomProductsWithCommonCategoriesFromRandomSellers(
                            VALID_PRODUCT_CATEGORIES,
                            NUMBER_OF_PRODUCTS);

                    Response response = getProductsByCategories(VALID_PRODUCT_CATEGORIES);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThatAllProductsHaveAtLeastOneCategoryFromGivenCategories(
                            filteredProductsResponse.products, VALID_PRODUCT_CATEGORIES);
                }

                @DisplayName(
                        "GIVEN empty category filter THEN returns no products and status " +
                                "200 ok")
                @Test
                void givenProductsWithoutCategories_whenFilteringProductsWithEmptyCategory_thenNoProductsReturnedWithStatus200() {
                    createRandomProductsWithoutCategoriesFromRandomSellers(NUMBER_OF_PRODUCTS);

                    Response response = getProductsByCategories(new ArrayList<>());
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThat(filteredProductsResponse.products).isEmpty();
                }
            }

            @DisplayName("By Minimum Price")
            @Nested
            class ByMinPrice {
                @DisplayName("GIVEN products with price greater or equal to minimum THEN returns " +
                        "all products and status 200 ok")
                @Test
                void givenProductsWithPriceGreaterOrEqualToMinPrice_whenFilteringProductsByMinPrice_thenAllProductsReturnedWithStatus200() {
                    createRandomProductsFromRandomSellersWithMinPrice(
                            A_VALID_PRODUCT_SUGGESTED_PRICE,
                            NUMBER_OF_PRODUCTS);

                    Response response = getProductsByMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThatAllProductsPriceIsGreaterOrEqualToMinPrice(
                            filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
                }

                @DisplayName(
                        "GIVEN products with price lesser than minimum THEN returns no products " +
                                "and status 200 ok")
                @Test
                void givenProductsWithPriceLesserThanMinPrice_whenFilteringProductsByMinPrice_thenNoProductsReturnedWithStatus200() {
                    createRandomProductsFromRandomSellersWithMaxPrice(
                            A_VALID_PRODUCT_SUGGESTED_PRICE,
                            NUMBER_OF_PRODUCTS);

                    Response response = getProductsByMinPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThatAllProductsPriceIsGreaterOrEqualToMinPrice(
                            filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
                }
            }

            @DisplayName("By Maximum Price")
            @Nested
            class ByMaxPrice {

                @DisplayName(
                        "GIVEN products with price lesser or equal to maximum THEN returns all " +
                                "products and status 200 ok")
                @Test
                void givenProductsWithPriceLesserOrEqualToMaxPrice_whenFilteringProductsByMaxPrice_thenAllProductsReturnedWithStatus200() {
                    createRandomProductsFromRandomSellersWithMaxPrice(
                            A_VALID_PRODUCT_SUGGESTED_PRICE,
                            NUMBER_OF_PRODUCTS);

                    Response response = getProductsByMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThatAllProductsPriceIsLesserOrEqualToMaxPrice(
                            filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
                }

                @DisplayName(
                        "GIVEN products with price greater than maximum THEN returns no product " +
                                "and status 200 ok")
                @Test
                void givenProductsWithPriceGreaterThanMaxPrice_whenFilteringProductsByMaxPrice_thenNoProductsReturnedWithStatus200() {
                    createRandomProductsFromRandomSellersWithMinPrice(
                            A_VALID_PRODUCT_SUGGESTED_PRICE,
                            NUMBER_OF_PRODUCTS);

                    Response response = getProductsByMaxPrice(A_VALID_PRODUCT_SUGGESTED_PRICE);
                    FilteredProductsResponse filteredProductsResponse =
                            response.as(FilteredProductsResponse.class);

                    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
                    assertThatAllProductsPriceIsLesserOrEqualToMaxPrice(
                            filteredProductsResponse.products, A_VALID_PRODUCT_SUGGESTED_PRICE);
                }
            }
        }
    }


}

