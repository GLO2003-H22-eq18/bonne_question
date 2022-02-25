package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Exceptions.InvalidArgumentExceptionMapper;
import ulaval.glo2003.Exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.Exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Product.UI.ProductResource;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.UI.SellerRequest;
import ulaval.glo2003.Seller.UI.SellerResource;

import java.io.IOException;
import java.net.URI;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.e2e.End2EndUtils.*;


class End2EndTests {

    @BeforeAll
    public static void startServer() throws IOException {
        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerResource sellerResource = new SellerResource(sellerRepository, sellerFactory);

        ProductRepository productRepository = new ProductRepository();
        ProductFactory productFactory = new ProductFactory(sellerRepository);
        ProductResource productResource = new ProductResource(productRepository, productFactory);

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(sellerResource)
                .register(productResource)
                .register(new InvalidArgumentExceptionMapper())
                .register(new MissingArgumentExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .packages("ulaval.glo2003");
        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }

    @Test
    void healthReturnsOk() {
        int responseStatus = getHealth();

        assertThat(responseStatus).isEqualTo(200);
    }

    @Test
    void givenSeller_whenCreatingSeller_thenExist() {
        SellerRequest sellerRequest = createValidSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.body()).isNotNull();
        assertThat(extractLocationId(response)).isNotNull();
        assertThat(extractLocationId(response)).isNotEmpty();
    }

    @Test
    void givenProduct_whenCreatingProduct_thenExist() {
        ProductRequest productRequest = createValidProduct();

        Response response = createProductResource(productRequest, createSellerGetId());

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.body()).isNotNull();
        assertThat(extractLocationId(response)).isNotNull();
        assertThat(extractLocationId(response)).isNotEmpty();
    }

    @Test
    void givenProductId_whenGettingProduct_thenProductReturned() throws Exception {
        String productId = createProductGetId();

        Response response = getProductById(productId);

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void givenSellerId_whenGettingSeller_thenSellerReturnedWithProduct() throws Exception {
        String sellerId = createSellerGetId();
        String productId = addProductToSellerGetId(sellerId);

        Response response = getSellerById(sellerId);

        System.out.println(response);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isNotNull();
    }

    @Test
    void givenSellerRequestWithMissingParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createMissingParamSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).isNotNull();
    }

    @Test
    void givenSellerRequestWithInvalidParam_whenCreatingSeller_thenError400() {
        SellerRequest sellerRequest = createInvalidParamSeller();

        Response response = createSellerResource(sellerRequest);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body()).isNotNull();
    }



}