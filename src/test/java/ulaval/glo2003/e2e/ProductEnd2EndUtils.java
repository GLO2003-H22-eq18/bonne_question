package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.FAKER;
import static ulaval.glo2003.e2e.End2EndUtils.RANDOM;
import static ulaval.glo2003.e2e.End2EndUtils.createResource;
import static ulaval.glo2003.e2e.End2EndUtils.extractLocationId;
import static ulaval.glo2003.e2e.End2EndUtils.getResourceByFilter;
import static ulaval.glo2003.e2e.End2EndUtils.getResourceById;
import static ulaval.glo2003.e2e.End2EndUtils.mixUpperAndLowerCase;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.A_VALID_SELLER_NAME;
import static ulaval.glo2003.e2e.SellerEnd2EndUtils.createRandomSellerGetId;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.ui.ProductRequest;
import ulaval.glo2003.product.ui.ProductResponse;
import ulaval.glo2003.subjects.OffsetDateTimeSubject;

public class ProductEnd2EndUtils {
    public final static int NUMBER_OF_PRODUCTS = 16;
    public static final String A_VALID_PRODUCT_TITLE = "Foobar";
    public static final String A_RANDOM_VALID_PRODUCT_TITLE = FAKER.commerce().productName();
    public static final String A_VALID_PRODUCT_DESCRIPTION = "An awesome generic product!";
    public static final Double A_VALID_PRODUCT_SUGGESTED_PRICE = 5.0;
    public static final List<String> VALID_PRODUCT_CATEGORIES =
            new ArrayList(List.of("beauty", "electronics"));
    public static final List<String> ALL_PRODUCT_CATEGORIES =
            new ArrayList(
                    List.of("beauty", "electronics", "other", "sports", "apparel", "housing"));
    public static final String A_INVALID_PRODUCT_TITLE = "    \n  \t \n ";
    public static final String A_INVALID_PRODUCT_DESCRIPTION = "    \n  \t \n ";
    public static final Double A_INVALID_PRODUCT_SUGGESTED_PRICE = 0.99;
    public static final List<String> A_INVALID_PRODUCT_CATEGORIES =
            new ArrayList(List.of("BeAuTy", "JavaBaby", "electronics"));
    public static final List<ProductCategory> PRODUCT_CATEGORIES_ENUM_VALUES =
            Arrays.asList(ProductCategory.values());
    public static final int MAXIMUM_NUMBER_OF_CATEGORIES = 5;
    public static final double MINIMUM_PRODUCT_PRICE = 1;
    public static final double MAXIMUM_PRODUCT_PRICE = 10000;
    public final static String PRODUCT_FILTER_SELLER_ID = "sellerId";
    public final static String PRODUCT_FILTER_TITLE = "title";
    public final static String PRODUCT_FILTER_CATEGORIES = "categories";
    public final static String PRODUCT_FILTER_MIN_PRICE = "minPrice";
    public final static String PRODUCT_FILTER_MAX_PRICE = "maxPrice";

    public static Response createProductResource(ProductRequest productRequest, String sellerId) {
        Headers productSeller = new Headers(new Header("X-Seller-Id", sellerId));
        return createResource("/products", productRequest, productSeller);
    }

    public static ProductRequest createValidProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = A_VALID_PRODUCT_TITLE;
        productRequest.description = A_VALID_PRODUCT_DESCRIPTION;
        productRequest.suggestedPrice = A_VALID_PRODUCT_SUGGESTED_PRICE;
        productRequest.categories = VALID_PRODUCT_CATEGORIES;

        return productRequest;
    }

    public static String createValidProductGetId(String sellerId) {
        ProductRequest productRequest = createValidProduct();
        Response response = createProductResource(productRequest, sellerId);
        return extractLocationId(response);
    }

    public static ProductRequest createRandomProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.title = FAKER.commerce().productName();
        productRequest.description = FAKER.lorem().sentence();
        productRequest.suggestedPrice = Double.parseDouble(
                FAKER.commerce().price(MINIMUM_PRODUCT_PRICE, MAXIMUM_PRODUCT_PRICE));
        productRequest.categories = getRandomCategories(MAXIMUM_NUMBER_OF_CATEGORIES);

        return productRequest;
    }

    public static String createRandomProductGetId() {
        String sellerId = createRandomSellerGetId();
        ProductRequest productRequest = createRandomProduct();

        Response response = createProductResource(productRequest, sellerId);

        return extractLocationId(response);
    }

    public static ProductResponse createRandomProductGetResponse() {
        String productId = createRandomProductGetId();

        Response response = getProductById(productId);

        return response.as(ProductResponse.class);
    }

    public static Map<String, String> getCorrespondingFilters(ProductResponse productResponse) {
        Map<String, String> correspondingFilters = new HashMap<>();
        correspondingFilters.put(PRODUCT_FILTER_SELLER_ID, productResponse.seller.id);
        correspondingFilters.put(PRODUCT_FILTER_TITLE, productResponse.title);
        correspondingFilters.put(PRODUCT_FILTER_MIN_PRICE,
                productResponse.suggestedPrice.toString());
        correspondingFilters.put(PRODUCT_FILTER_MAX_PRICE,
                productResponse.suggestedPrice.toString());
        for (String category : productResponse.categories) {
            correspondingFilters.put(PRODUCT_FILTER_CATEGORIES, category);
        }

        return correspondingFilters;
    }

    public static void createRandomProductsFromRandomSellersWithTitle(String title,
                                                                      int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            productRequest.title = mixUpperAndLowerCase(FAKER.letterify("??? " + title + " ???"));
            createProductResource(productRequest, createRandomSellerGetId());
        }
    }

    public static void createRandomProductsWithCommonCategoriesFromRandomSellers(
            List<String> categories, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            productRequest.categories = getRandomCategories(MAXIMUM_NUMBER_OF_CATEGORIES);
            productRequest.categories.add(categories.get(RANDOM.nextInt(categories.size())));

            createProductResource(productRequest, createRandomSellerGetId());
        }
    }

    public static void createRandomProductsWithoutCategoriesFromRandomSellers(
            int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            productRequest.categories = new ArrayList<>();

            createProductResource(productRequest, createRandomSellerGetId());
        }
    }

    public static void createRandomProductsFromRandomSellersWithMinPrice(double minPrice,
                                                                         int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            productRequest.suggestedPrice =
                    FAKER.number().randomDouble(2, (int) minPrice + 1, (int) MAXIMUM_PRODUCT_PRICE);
            createProductResource(productRequest, createRandomSellerGetId());
        }
    }

    public static void createRandomProductsFromRandomSellersWithMaxPrice(double maxPrice,
                                                                         int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            productRequest.suggestedPrice =
                    FAKER.number().randomDouble(2, (int) MINIMUM_PRODUCT_PRICE, (int) maxPrice - 1);
            createProductResource(productRequest, createRandomSellerGetId());
        }
    }

    public static void createRandomProductsFromRandomSellers(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            ProductRequest productRequest = createRandomProduct();
            createProductResource(productRequest, createRandomSellerGetId());
        }
    }


    public static ProductRequest createValidProductWithoutCategories() {
        ProductRequest productRequest = createValidProduct();
        productRequest.categories = VALID_PRODUCT_CATEGORIES;
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

    public static Response getProducts() {
        return getResourceByFilter("/products", "", "");
    }

    public static Response getProducts(Map<String, String> filters) {
        return getResourceByFilter("/products", filters);
    }

    public static Response getProductById(String productId) {
        return getResourceById("/products/{productId}", productId);
    }

    public static Response getProductsBySellerId(String sellerId) {
        String filterName = "sellerId";
        return getResourceByFilter("/products", filterName, sellerId);
    }

    public static Response getProductsByTitle(String title) {
        String filterName = "title";
        return getResourceByFilter("/products", filterName, title);
    }

    public static Response getProductsByCategories(List<String> categories) {
        String filterName = "categories";
        return getResourceByFilter("/products", filterName, categories);
    }

    public static Response getProductsByMinPrice(Double minPrice) {
        String filterName = "minPrice";
        return getResourceByFilter("/products", filterName, minPrice);
    }

    public static Response getProductsByMaxPrice(Double maxPrice) {
        String filterName = "maxPrice";
        return getResourceByFilter("/products", filterName, maxPrice);
    }

    public static List<String> getRandomCategories(int maximumNumberOfCategories) {
        List<ProductCategory> randomCategories = new ArrayList<>();
        for (int i = 0; i < RANDOM.nextInt(maximumNumberOfCategories); i++) {
            randomCategories.add(PRODUCT_CATEGORIES_ENUM_VALUES.get(
                    RANDOM.nextInt(PRODUCT_CATEGORIES_ENUM_VALUES.size())));
        }

        return ProductCategory.toStringList(randomCategories);
    }

    public static void assertThatProductResponseFieldsAreValid(ProductResponse productResponse,
                                                               String expectedProductId,
                                                               String expectedSellerId) {
        assertThat(productResponse.seller.id).isEqualTo(expectedSellerId);
        assertThat(productResponse.seller.name).isEqualTo(A_VALID_SELLER_NAME);
        assertThat(productResponse.id).isEqualTo(expectedProductId);
        OffsetDateTimeSubject.assertThat(productResponse.createdAt).isWithinExpectedRange();
        assertThat(productResponse.title).isEqualTo(A_VALID_PRODUCT_TITLE);
        assertThat(productResponse.description).isEqualTo(A_VALID_PRODUCT_DESCRIPTION);
        assertThat(productResponse.suggestedPrice).isEqualTo(A_VALID_PRODUCT_SUGGESTED_PRICE);
        assertThat(productResponse.categories).containsExactlyElementsIn(VALID_PRODUCT_CATEGORIES);
        assertThat(productResponse.offers.count).isEqualTo(0);
    }

    public static void assertThatAllProductsHaveTheSameSellerId(
            List<ProductResponse> filteredProducts, String sellerId) {
        for (ProductResponse product : filteredProducts) {
            assertThat(product.seller.id).isEqualTo(sellerId);
        }
    }

    public static void assertThatAllProductsHaveAtLeastOneCategoryFromGivenCategories(
            List<ProductResponse> filteredProducts,
            List<String> givenCategories) {
        for (ProductResponse product : filteredProducts) {
            assertThat(product.categories).containsAnyIn(givenCategories);
        }
    }

    public static void assertThatAllProductsPriceIsGreaterOrEqualToMinPrice(
            List<ProductResponse> filteredProducts,
            double minPrice) {
        for (ProductResponse product : filteredProducts) {
            assertThat(product.suggestedPrice).isAtLeast(minPrice);
        }
    }

    public static void assertThatAllProductsPriceIsLesserOrEqualToMaxPrice(
            List<ProductResponse> filteredProducts,
            double maxPrice) {
        for (ProductResponse product : filteredProducts) {
            assertThat(product.suggestedPrice).isAtMost(maxPrice);
        }
    }

}