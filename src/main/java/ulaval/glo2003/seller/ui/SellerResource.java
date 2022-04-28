package ulaval.glo2003.seller.ui;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.bson.types.ObjectId;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerAssembler;
import ulaval.glo2003.seller.ui.assemblers.SellerAssembler;
import ulaval.glo2003.seller.ui.requests.SellerRequest;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;
import ulaval.glo2003.seller.ui.responses.SellerResponse;
import ulaval.glo2003.utils.ObjectIdUtil;

@Path("/sellers")
public class SellerResource {
    private final SellerRepository sellerRepository;
    private final SellerFactory sellerFactory;
    private final SellerAssembler sellerAssembler;
    private final CurrentSellerAssembler currentSellerAssembler;

    public SellerResource(
            SellerRepository sellerRepository,
            SellerFactory sellerFactory,
            SellerAssembler sellerAssembler,
            CurrentSellerAssembler currentSellerAssembler) {
        this.sellerRepository = sellerRepository;
        this.sellerFactory = sellerFactory;
        this.sellerAssembler = sellerAssembler;
        this.currentSellerAssembler = currentSellerAssembler;
    }

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        ObjectId sellerObjectId = ObjectIdUtil.createValidObjectId(sellerId, Seller.class);
        Seller seller =
                sellerRepository.findById(sellerObjectId);

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

    @GET
    @Path("/@me")
    public Response getCurrentSeller(
            @HeaderParam("X-Seller-Id") String sellerId) {
        ObjectId sellerObjectId = ObjectIdUtil.createValidObjectId(sellerId, Seller.class);
        Seller seller = sellerRepository.findById(sellerObjectId);

        CurrentSellerResponse currentSellerResponse =
                currentSellerAssembler.createCurrentSellerResponse(seller);

        return Response.status(200).entity(currentSellerResponse).build();
    }

    @GET
    @Path("/@me/views")
    public Response getCurrentSellerViews(
            @HeaderParam("X-Seller-Id") String sellerId) {
        ObjectId sellerObjectId = ObjectIdUtil.createValidObjectId(sellerId, Seller.class);
        Seller seller = sellerRepository.findById(sellerObjectId);

        CurrentSellerResponse currentSellerResponse =
                currentSellerAssembler.createCurrentSellerViewsResponse(seller);

        return Response.status(200).entity(currentSellerResponse).build();
    }
}
