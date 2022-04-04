package ulaval.glo2003.e2e;


import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import org.apache.http.HttpStatus;
import org.bson.types.ObjectId;
import ulaval.glo2003.exceptions.ErrorCode;
import ulaval.glo2003.exceptions.ErrorResponse;

public class End2EndUtils {

    public final static Faker FAKER = new Faker();
    public static final ObjectId A_INVALID_ID = new ObjectId();
    public static final Random RANDOM = new Random();

    public static void assertThatPostResponseIsValid(Response postResponse) {
        assertThat(postResponse.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        assertThat(isNullOrEmpty(extractLocationId(postResponse))).isFalse();
        assertThat(isNullOrEmpty(postResponse.body().asString())).isTrue();
    }

    public static void assertThatResponseIsItemNotFoundError(Response response) {
        assertThatErrorResponseIsValid(response, HttpStatus.SC_NOT_FOUND,
                ErrorCode.ITEM_NOT_FOUND);
    }

    public static void assertThatResponseIsMissingParamError(Response response) {
        assertThatErrorResponseIsValid(response, HttpStatus.SC_BAD_REQUEST,
                ErrorCode.MISSING_PARAMETER);
    }

    public static void assertThatResponseIsInvalidParamError(Response response) {
        assertThatErrorResponseIsValid(response, HttpStatus.SC_BAD_REQUEST,
                ErrorCode.INVALID_PARAMETER);
    }

    public static void assertThatErrorResponseIsValid(Response response, int expectedStatus,
                                                       ErrorCode expectedCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatus);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertThat(errorResponse.description).isNotNull();
        assertThat(errorResponse.code).isEqualTo(expectedCode);
    }

    public static Response createResource(String path, Object request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public static Response createResource(String path, Object request, String pathParam, String resourceId) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam(pathParam, resourceId)
                .body(request)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public static Response createResource(String path, Object request, Headers header) {
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

    public static Response getResourceById(String pathParam, String resourceId) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(pathParam, resourceId)
                .then()
                .extract()
                .response();
    }

    public static Response getResourceById(String path, Headers header) {
        return given()
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .headers(header)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static Response getResourceByFilter(String path, String filterName,
                                                Object filterValue) {
        return given()
                .queryParam(filterName, filterValue)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static Response getResourceByFilter(String path, String filterName,
                                                Collection filterValue) {
        return given()
                .queryParam(filterName, filterValue)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static Response getResourceByFilter(String path, Map<String, String> filters) {
        return given()
                .queryParams(filters)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static String extractLocationId(Response response) {
        String location = response.header("Location");
        return location.substring(location.lastIndexOf("/") + 1);
    }

    public static String mixUpperAndLowerCase(String string) {
        char[] letters = string.toCharArray();
        StringBuilder randomizedString = new StringBuilder(letters.length);
        for (int i = 0; i < letters.length; i++) {
            if (i % 2 == 0) {
                randomizedString.append(Character.toLowerCase(letters[i]));
            } else {
                randomizedString.append(Character.toUpperCase(letters[i]));
            }
        }

        return randomizedString.toString();
    }
}

