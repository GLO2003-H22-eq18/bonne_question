package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.Exceptions.InvalidSellerExceptionMapper;
import ulaval.glo2003.Exceptions.MissingArgumentExceptionMapper;
import ulaval.glo2003.Exceptions.ItemNotFoundExceptionMapper;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig()
                .register(new InvalidSellerExceptionMapper())
                .register(new MissingArgumentExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .packages("ulaval.glo2003");
        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
