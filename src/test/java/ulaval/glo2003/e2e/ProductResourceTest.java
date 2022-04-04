package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.A_INVALID_ID;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatPostResponseIsValid;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsInvalidParamError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsItemNotFoundError;
import static ulaval.glo2003.e2e.End2EndUtils.assertThatResponseIsMissingParamError;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_RANDOM_VALID_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_VALID_OFFER_AMOUNT;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_VALID_PRODUCT_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.NUMBER_OF_PRODUCTS;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.VALID_PRODUCT_CATEGORIES;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.assertThatAllProductsHaveAtLeastOneCategoryFromGivenCategories;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.assertThatAllProductsHaveTheSameSellerId;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.assertThatAllProductsPriceIsGreaterOrEqualToMinPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.assertThatAllProductsPriceIsLesserOrEqualToMaxPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.assertThatProductResponseFieldsAreValid;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferResource;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithInvalidAmount;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithInvalidBuyerEmail;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithInvalidBuyerName;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithInvalidBuyerPhone;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithInvalidMessage;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createOfferWithMissingParams;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductResource;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithInvalidCategories;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithInvalidDescription;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithInvalidPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithInvalidTitle;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductWithMissingParams;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomOffer;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductGetId;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductGetResponse;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductsFromRandomSellers;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductsFromRandomSellersWithMaxPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductsFromRandomSellersWithMinPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductsFromRandomSellersWithTitle;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProductsWithCommonCategoriesFromRandomSellers;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createValidOffer;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createValidProduct;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createValidProductGetId;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createValidProductWithoutCategories;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getCorrespondingFilters;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductById;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProducts;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductsByCategories;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductsByMaxPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductsByMinPrice;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductsBySellerId;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.getProductsByTitle;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.addRandomProductsToSeller;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createRandomSellerGetId;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createValidSellerGetId;

import io.restassured.response.Response;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.bson.types.ObjectId;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.Main;
import ulaval.glo2003.product.ui.requests.OfferRequest;
import ulaval.glo2003.product.ui.responses.FilteredProductsResponse;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;
import ulaval.glo2003.product.ui.responses.ProductResponse;

@DisplayName("Product Resource")
public class ProductResourceTest {

    public static HttpServer server;
    public static ApplicationContext applicationContext = new ApplicationContext();

    @BeforeAll
    public static void startServer() throws IOException {
        server = Main.startServer(applicationContext);
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
            Response response = getProductById(A_INVALID_ID.toString());

            assertThatResponseIsItemNotFoundError(response);
        }

        @DisplayName("GIVEN product with offer THEN returns product with offer and status 200 ok")
        @Test
        void givenValidProductWithOffer_whenGettingProduct_thenProductWithOfferIsReturnedWithStatus200() {
            String productId = createValidProductGetId(createRandomSellerGetId());
            OfferRequest offerRequest = createRandomOffer(A_VALID_PRODUCT_SUGGESTED_PRICE);

            Response response = createOfferResource(offerRequest, productId);
            ProductOffersResponse offersResponse = getProductById(productId).as(ProductResponse.class).offers;

            assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(offersResponse.count).isEqualTo(1);
            assertThat(offersResponse.mean).isGreaterThan(0);
        }

        @DisplayName("WHEN filtering")
        @Nested
        class WhenFilteringProduct {

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

                    Response response = getProductsBySellerId(A_INVALID_ID.toString());
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
                    assertThat(filteredProductsResponse.products.size()).isAtLeast(
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

        @DisplayName("WHEN adding an offer")
        @Nested
        class WhenAddingOffer {

            @DisplayName("GIVEN valid offer THEN adds offer to product with status 200 ok")
            @Test
            void givenValidOfferRequest_whenAddingProductOffer_thenOfferIsAddedToProductWithStatus200() {
                OfferRequest offerRequest = createValidOffer();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
            }

            @DisplayName("GIVEN no required parameters THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithMissingParam_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithMissingParams();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsMissingParamError(response);
            }

            @DisplayName("GIVEN invalid buyer name THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithInvalidBuyerName_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithInvalidBuyerName();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsInvalidParamError(response);
            }

            @DisplayName("GIVEN invalid buyer email THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithInvalidBuyerEmail_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithInvalidBuyerEmail();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsInvalidParamError(response);
            }

            @DisplayName("GIVEN invalid buyer phone THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithInvalidBuyerPhone_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithInvalidBuyerPhone();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsInvalidParamError(response);
            }

            @DisplayName("GIVEN invalid message THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithInvalidMessage_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithInvalidMessage();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsInvalidParamError(response);
            }

            @DisplayName("GIVEN invalid amount THEN returns error 400 bad request")
            @Test
            void givenOfferRequestWithInvalidAmount_whenAddingProductOffer_thenReturnsError400() {
                OfferRequest offerRequest = createOfferWithInvalidAmount();
                String productId = createRandomProductGetId();

                Response response = createOfferResource(offerRequest, productId);

                assertThatResponseIsInvalidParamError(response);
            }
        }
    }
}
