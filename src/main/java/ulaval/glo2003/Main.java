package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.exceptions.InvalidArgumentExceptionMapper;
import ulaval.glo2003.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.product.domain.ProductFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.ui.assemblers.ProductAssembler;
import ulaval.glo2003.product.ui.ProductResource;
import ulaval.glo2003.seller.domain.SellerFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerAssembler;
import ulaval.glo2003.seller.ui.assemblers.SellerAssembler;
import ulaval.glo2003.seller.ui.SellerResource;

public class Main {

    public static final String BASE_URI = "http://0.0.0.0:";

    public static HttpServer startServer() {

        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerAssembler sellerAssembler = new SellerAssembler();
        CurrentSellerAssembler currentSellerAssembler = new CurrentSellerAssembler();
        SellerResource sellerResource =
                new SellerResource(sellerRepository, sellerFactory, sellerAssembler,
                        currentSellerAssembler);

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

        String port = Objects.requireNonNullElse(System.getenv("PORT"), "8080");
        String uri = BASE_URI + port + "/";

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), resourceConfig);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        server.start();
    }
}