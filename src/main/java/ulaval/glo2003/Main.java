package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.exceptions.InvalidArgumentExceptionMapper;
import ulaval.glo2003.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.product.domain.ProductFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.ui.ProductAssembler;
import ulaval.glo2003.product.ui.ProductResource;
import ulaval.glo2003.seller.domain.MongoSellersRepository;
import ulaval.glo2003.seller.domain.SellerFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.ui.SellerAssembler;
import ulaval.glo2003.seller.ui.SellerProductAssembler;
import ulaval.glo2003.seller.ui.SellerResource;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        MongoSellersRepository mongoSellersRepository = new MongoSellersRepository();
        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerProductAssembler sellerProductAssembler = new SellerProductAssembler();
        SellerAssembler sellerAssembler = new SellerAssembler(sellerProductAssembler);
        SellerResource sellerResource =
                new SellerResource(sellerRepository, sellerFactory, sellerAssembler);

        ProductRepository productRepository = new ProductRepository();
        ProductFactory productFactory = new ProductFactory();
        ProductAssembler productAssembler = new ProductAssembler();
        ProductResource productResource = new ProductResource(
                sellerRepository, productRepository, productFactory, productAssembler);

        final ResourceConfig resourceConfig = new ResourceConfig()
                .register(sellerResource)
                .register(productResource)
                .register(new InvalidArgumentExceptionMapper())
                .register(new MissingArgumentExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .packages("ulaval.glo2003");

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        server.start();
    }
}
