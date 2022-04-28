package ulaval.glo2003;

import com.mongodb.MongoException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

@Path("/health")
public class HealthResource {

    private final SellerRepository sellerRepository;

    public HealthResource(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @GET
    public Response getHealth() {
        Map<String, Boolean> response = new HashMap<>();
        response.put("api", true);

        try {
            Map<ObjectId, Seller> sellers = sellerRepository.getSellers();
        } catch (MongoException e) {
            response.put("db", false);
            return Response.status(200).entity(response).build();
        }

        response.put("db", true);
        return Response.ok().entity(response).build();
    }
}
