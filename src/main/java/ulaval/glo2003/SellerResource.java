package ulaval.glo2003;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Exceptions.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

@Path("/sellers")
public class SellerResource {
    private static final Collection<Seller> sellers = new ArrayList<>();
    private static int sellerId = 0;

    @GET
    @Path("/{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller seller = sellers.stream().filter(item -> item.id.equals(sellerId)).findAny().orElseThrow(SellerNotFoundException::new);
        String jsonResponse = String.format("{\n\t\"id\": \"%s\",\n\t\"name\": \"%s\",\n\t\"bio\": \"%s\",\n\t\"createdAt\": \"%s\",\n\t\"products\": []\n}",
                seller.id, seller.name, seller.bio, seller.createdAt.toString());

        return Response.status(200).entity(jsonResponse).build();
    }

    @POST
    public Response postSeller(SellerRequest sellerRequest,
                               @Context UriInfo uri) {

        OffsetDateTime creationDate = OffsetDateTime.now(Clock.systemUTC());
        String newSellerId = String.valueOf(sellerId);

        checkMissingParam(sellerRequest);
        checkInvalidParam(sellerRequest);
        checkInvalidId(newSellerId);

        sellers.add(new Seller(newSellerId, creationDate, sellerRequest.name, sellerRequest.bio, new ArrayList<String>()));

        sellerId += 1;

        return Response.status(201).header("Location", uri.getPath()).build();
    }

    private void checkInvalidId(String sellerId) {
        sellers.forEach(seller -> {
            if (seller.id.equals(sellerId))
                throw new InvalidSellerIdException();
        });
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
        validateBirthdate(sellerRequest.birthDate);
    }

    private void validateName(String name){
        if(removeEmptyChar(name).isEmpty())
            throw new InvalidSellerNameException();
    }

    private void validateBio(String bio){
        if(removeEmptyChar(bio).isEmpty())
            throw new InvalidSellerBioException();
    }

    private void validateBirthdate(LocalDate birthDate){
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