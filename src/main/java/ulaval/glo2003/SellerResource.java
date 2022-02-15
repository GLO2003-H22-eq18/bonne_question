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

        checkMissingParam(sellerRequest);
        checkInvalidParam(sellerRequest);

        Seller newSeller = new Seller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate, new ArrayList<String>());
        sellers.add(newSeller);

        return Response.status(201).header("Location", uri.getPath() + String.format("/%s", newSeller.getId())).build();
    }

    private void checkMissingParam(SellerRequest sellerRequest){
        if(sellerRequest.bio == null)
            throw new MissingSellerBioException();
        else if(sellerRequest.birthDate == null)
            throw new MissingSellerBirthdateException();
        else if(sellerRequest.name == null)
            throw new MissingSellerNameException();
    }

    private void checkInvalidParam(SellerRequest sellerRequest){
        validateName(sellerRequest.name);
        validateBio(sellerRequest.bio);
        validateBirthdateString(sellerRequest.birthDate);
    }

    private void validateName(String name){
        if(removeEmptyChar(name).isEmpty())
            throw new InvalidSellerNameException();
    }

    private void validateBio(String bio){
        if(removeEmptyChar(bio).isEmpty())
            throw new InvalidSellerBioException();
    }

    private void validateBirthdateString(String birthDateString){
        LocalDate birthDate = LocalDate.parse(birthDateString);
        Period period = Period.between(birthDate, LocalDate.now());
        if(period.getYears() < 18)
            throw new InvalidSellerBirthdateException();
    }

    private String removeEmptyChar(String string){
         return string
                 .replaceAll("\n", "")
                 .replaceAll("\t", "")
                 .replaceAll(" ", "")
                 .replaceAll("0", "");
    }
}