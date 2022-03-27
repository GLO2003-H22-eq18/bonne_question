package ulaval.glo2003.seller.ui;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.exceptions.SellerNotFoundException;
import ulaval.glo2003.seller.ui.assemblers.SellerAssembler;
import ulaval.glo2003.seller.ui.requests.SellerRequest;
import ulaval.glo2003.seller.ui.responses.SellerResponse;

@Path("/sellers")
public class SellerResource {
    private final SellerRepository sellerRepository;
    private final SellerFactory sellerFactory;
    private final SellerAssembler sellerAssembler;

    public SellerResource(
            SellerRepository sellerRepository,
            SellerFactory sellerFactory,
            SellerAssembler sellerAssembler) {
        this.sellerRepository = sellerRepository;
        this.sellerFactory = sellerFactory;
        this.sellerAssembler = sellerAssembler;
    }

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller seller =
                sellerRepository.findById(sellerId);

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);

        return Response.status(200).entity(sellerResponse).build();
    }

    @POST
    public Response postSeller(SellerRequest sellerRequest, @Context UriInfo uri) {
        Seller mySeller = sellerFactory.create(sellerRequest);
        sellerRepository.save(mySeller);

        return Response.status(201)
                .header("Location", uri.getPath() + "/" + mySeller.getId())
                .build();
    }
}
