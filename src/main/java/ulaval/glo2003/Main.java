package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.Exceptions.InvalidArgumentExceptionMapper;
import ulaval.glo2003.Exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.Exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;
import ulaval.glo2003.Product.UI.ProductAssembler;
import ulaval.glo2003.Product.UI.ProductResource;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.UI.SellerAssembler;
import ulaval.glo2003.Seller.UI.SellerProductAssembler;
import ulaval.glo2003.Seller.UI.SellerResource;

public class Main {

    public static void main(String[] args) throws IOException {
        SellerRepository sellerRepository = new SellerRepository();
        SellerFactory sellerFactory = new SellerFactory();
        SellerProductAssembler sellerProductAssembler = new SellerProductAssembler();
        SellerAssembler sellerAssembler = new SellerAssembler(sellerProductAssembler);
        SellerResource sellerResource =
                new SellerResource(sellerRepository, sellerFactory, sellerAssembler);

        ProductRepository productRepository = new ProductRepository();
        ProductFactory productFactory = new ProductFactory();
        ProductAssembler productAssembler = new ProductAssembler();
        ProductResource productResource =
                new ProductResource(
                        sellerRepository, productRepository, productFactory, productAssembler);

        ResourceConfig resourceConfig =
                new ResourceConfig()
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
}
