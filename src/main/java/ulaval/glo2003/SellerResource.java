package ulaval.glo2003;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Path("/sellers")
public class SellerResource {
    Collection<Seller> sellers = new ArrayList<>();

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        return Response.status(200).build();
    }

    @POST
    @Path("/{sellerId}")
    public Response postSeller(@PathParam("sellerId") String sellerId,
                               SellerRequest sellerRequest,
                               @Context UriInfo uri) {
        OffsetDateTime createdAt = OffsetDateTime.now(Clock.systemUTC());
        sellers.add(new Seller(sellerId, createdAt, sellerRequest.name, sellerRequest.bio));

        String url = uri.getPath();
        return Response.status(201).header("Location", url).build();
    }
}
