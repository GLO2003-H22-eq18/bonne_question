package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.FAKER;
import static ulaval.glo2003.e2e.End2EndUtils.createResource;
import static ulaval.glo2003.e2e.End2EndUtils.extractLocationId;
import static ulaval.glo2003.e2e.End2EndUtils.getResourceById;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_VALID_PRODUCT_DESCRIPTION;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_VALID_PRODUCT_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.A_VALID_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.VALID_PRODUCT_CATEGORIES;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createProductResource;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createRandomProduct;
import static ulaval.glo2003.e2e.ProductEnd2EndUtils.createValidProduct;

import io.restassured.response.Response;
import java.time.ZoneId;
import java.util.stream.IntStream;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;
import ulaval.glo2003.seller.ui.requests.SellerRequest;
import ulaval.glo2003.seller.ui.responses.SellerResponse;
import ulaval.glo2003.subjects.OffsetDateTimeSubject;

public class SellerEnd2EndUtils {
    public static final String A_VALID_SELLER_NAME = "John Cena";
    public static final String A_VALID_SELLER_BIO = "What a chad!";
    public static final String A_VALID_SELLER_BIRTHDATE = "1997-04-23";
    public static final String A_INVALID_SELLER_NAME = "    \n  \t \n ";
    public static final String A_INVALID_SELLER_BIO = "    \n  \t \n ";
    public static final String A_INVALID_SELLER_BIRTHDATE = "2100-11-01";

    public static Response createSellerResource(SellerRequest sellerRequest) {
        return createResource("/sellers", sellerRequest);
    }

    public static SellerRequest createValidSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = A_VALID_SELLER_NAME;
        sellerRequest.bio = A_VALID_SELLER_BIO;
        sellerRequest.birthDate = A_VALID_SELLER_BIRTHDATE;
        return sellerRequest;
    }

    public static String createValidSellerGetId() {
        SellerRequest sellerRequest = createValidSeller();
        Response response = createResource("/sellers", sellerRequest);
        return extractLocationId(response);
    }

    public static SellerRequest createRandomSeller() {
        SellerRequest sellerRequest = new SellerRequest();
        sellerRequest.name = FAKER.harryPotter().character();
        sellerRequest.bio = FAKER.harryPotter().quote();
        sellerRequest.birthDate = FAKER.date().birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString();
        return sellerRequest;
    }

    public static String createRandomSellerGetId() {
        SellerRequest sellerRequest = createRandomSeller();
        Response response = createResource("/sellers", sellerRequest);
        return extractLocationId(response);
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

    public static Response getSellerById(String sellerId) {
        return getResourceById("/sellers/{sellerId}", sellerId);
    }

    public static String addProductToSellerGetId(String sellerId) {
        Response response = createProductResource(createValidProduct(), sellerId);
        return extractLocationId(response);
    }

    public static void addRandomProductsToSeller(String sellerId, int numberOfProducts) {
        IntStream.range(0, numberOfProducts).forEach(
                i -> createProductResource(createRandomProduct(), sellerId));
    }

    public static void assertThatSellerWithProductResponseFieldsAreValid(
            SellerResponse sellerResponse,
            String expectedSellerId,
            String expectedProductId) {
        assertThatSellerResponseFieldsAreValid(sellerResponse, expectedSellerId);

        SellerProductResponse sellerProductResponse = sellerResponse.products.get(0);
        assertThatSellerProductResponseFieldsAreValid(sellerProductResponse, expectedProductId);
    }

    public static void assertThatSellerResponseFieldsAreValid(SellerResponse sellerResponse,
                                                              String expectedSellerId) {
        assertThat(sellerResponse.id).isEqualTo(expectedSellerId);
        assertThat(sellerResponse.name).isEqualTo(A_VALID_SELLER_NAME);
        assertThat(sellerResponse.bio).isEqualTo(A_VALID_SELLER_BIO);
        OffsetDateTimeSubject.assertThat(sellerResponse.createdAt).isWithinExpectedRange();
    }

    public static void assertThatSellerProductResponseFieldsAreValid(
            SellerProductResponse productResponse, String expectedProductId) {
        assertThat(productResponse.id).isEqualTo(expectedProductId);
        OffsetDateTimeSubject.assertThat(productResponse.createdAt).isWithinExpectedRange();
        assertThat(productResponse.title).isEqualTo(A_VALID_PRODUCT_TITLE);
        assertThat(productResponse.description).isEqualTo(A_VALID_PRODUCT_DESCRIPTION);
        assertThat(productResponse.suggestedPrice).isEqualTo(A_VALID_PRODUCT_SUGGESTED_PRICE);
        assertThat(productResponse.categories).containsExactlyElementsIn(VALID_PRODUCT_CATEGORIES);
        assertThat(productResponse.offers.count).isEqualTo(0);
    }
}
