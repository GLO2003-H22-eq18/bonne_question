package ulaval.glo2003.Seller.UI;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerFactory;
import ulaval.glo2003.Seller.Domain.SellerRepository;
import ulaval.glo2003.Seller.Exceptions.SellerNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

@Path("/sellers")
public class SellerResource {
    private final SellerAssembler sellerAssembler;
    private final SellerRepository sellerRepository;
    private final SellerFactory sellerFactory;

    public SellerResource(SellerRepository sellerRepository, SellerFactory sellerFactory, SellerAssembler sellerAssembler) {
        this.sellerRepository = sellerRepository;
        this.sellerFactory = sellerFactory;
        this.sellerAssembler = sellerAssembler;
    }

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller seller = sellerRepository.getSellers().entrySet()
                .stream()
                .filter(map -> map.getKey().equals(sellerId))
                .findAny()
                .orElseThrow(SellerNotFoundException::new)
                .getValue();

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);

        return Response.status(200).entity(sellerResponse).build();
    }

    @POST
    public Response postSeller(SellerRequest sellerRequest,
                               @Context UriInfo uri) {
        Seller mySeller = sellerFactory.create(sellerRequest);
        sellerRepository.save(mySeller);

        return Response.status(201).header("Location", uri.getPath() + "/" + mySeller.getId()).build();
    }
}