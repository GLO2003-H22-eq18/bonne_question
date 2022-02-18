package ulaval.glo2003;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Exceptions.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

@Path("/sellers")
public class SellerResource {
    private static final Collection<Seller> sellers = new ArrayList<>();
    private static final SellerAssembler sellerAssembler = new SellerAssembler();

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller seller = sellers.stream().filter(item -> item.id.equals(sellerId)).findAny().orElseThrow(SellerNotFoundException::new);

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);

        return Response.status(200).entity(sellerResponse).build();
    }

    @POST
    public Response postSeller(SellerRequest sellerRequest,
                               @Context UriInfo uri) {
        SellerFactory sellerFactory = new SellerFactory();
        Seller mySeller = sellerFactory.create(sellerRequest);

        sellers.add(mySeller);

        return Response.status(201).header("Location", uri.getPath() + "/" + mySeller.getId()).build();
    }
}