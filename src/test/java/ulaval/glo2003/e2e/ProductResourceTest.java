package ulaval.glo2003.e2e;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Main;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.UI.SellerRequest;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

//import static spark.Spark.stop; TODO

public class ProductResourceTest {

    private Seller defaultSeller;
    private int sellerId;

    @BeforeAll
    public static void startServer() throws IOException {
        Main.main(new String[] {});
    }

    @BeforeEach
    public void createDefaultSeller(){
        Seller defaultSeller = new Seller("John Cena",
                    "What a chad!",
                    OffsetDateTime.now(Clock.systemUTC()),
                    LocalDate.parse("1977-04-23"),
                    new ArrayList<>());
    }

    @AfterAll
    static void tearDownServer() {
        //stop();  TODO NO NECESSARY?
    }

    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpoint_thenCorrect(){


        ArrayList<String> listOfCategories = new ArrayList<>();

        ProductRequest productRequest = new ProductRequest();
        productRequest.title = "";
        productRequest.description = "";
        productRequest.suggestedPrice = 5.0;
        productRequest.categories = new ArrayList<>();
    }


    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpointWithMissingField_thenMissingParameterError() {
        //TODO
    }

    @Test
    void givenProduct_whenMakingPOSTRequestToProductEndpointWithInvalidField_thenInvalidParameterError() {
        //TODO
    }

    @Test
    void whenMakingGETResquestToProductEndpoint_thenCorrect(){
        //TODO 200
    }

    @Test
    void whenMakingGETRequestToProductEndpointWithAbsentProductId_thenItemNotFoundError() {
        //TODO
    }

    @Test
    void whenMakingGETResquestToProductEndpointWithFilter_thenCorrect(){
        //TODO 200
    }







}
