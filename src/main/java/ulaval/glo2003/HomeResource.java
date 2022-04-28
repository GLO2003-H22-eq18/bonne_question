package ulaval.glo2003;

import com.mongodb.MongoException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HomeResource {

    public HomeResource() {}

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewHome() throws FileNotFoundException {
        File f = new File("api/index.html");
        return new FileInputStream(f);
    }


}
