package ulaval.glo2003;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/sellers")
public class SellerResource {

    @POST
    @Path("/{sellerId}")
    public Response postSeller(@PathParam("sellerId") String sellerId,
                               SellerRequest sellerRequest,
                               @Context UriInfo uri) {
        String url = uri.getPath();

        return Response.status(201).header("Location", url).build();
    }
}
