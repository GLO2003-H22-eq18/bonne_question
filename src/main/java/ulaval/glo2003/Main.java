package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.Exceptions.InvalidArgumentExceptionMapper;
import ulaval.glo2003.Exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.Exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;
import ulaval.glo2003.Product.UI.ProductResource;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.UI.SellerResource;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerResource sellerResource = new SellerResource(sellerRepository, sellerFactory);

        ProductRepository productRepository = new ProductRepository();
        ProductFactory productFactory = new ProductFactory(sellerRepository);
        ProductResource productResource = new ProductResource(productRepository, productFactory);

        final ResourceConfig resourceConfig = new ResourceConfig()
                .register(sellerResource)
                .register(productResource)
                .register(new InvalidArgumentExceptionMapper())
                .register(new MissingArgumentExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .packages("ulaval.glo2003");

        LOGGER.info("Starting Server........");

        final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);

        return httpServer;
    }

    public static void main(String[] args) throws IOException {
            final HttpServer server = startServer();

            server.start();

            LOGGER.info("[HttpServer] Running @ " + BASE_URI);
    }
}
