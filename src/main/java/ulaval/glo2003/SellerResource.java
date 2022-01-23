package ulaval.glo2003;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Path("/sellers")
public class SellerResource {
    @POST
    @Path("/{sellerId}")
    public Response postSeller(@PathParam("sellerId") String sellerId, SellerRequest sellerRequest,
                               @Context UriInfo uri){
        Response response;
        String url = uri.getPath();

        if (sellerRequest.bio == null || sellerRequest.name == null || sellerRequest.birthDate == null){
            String jsonResponse = "{\n\tcode: MISSING_PARAM,\n\tdescription: un paramètre (URL, header, JSON, etc.) est manquant\n}";
            response = Response.status(400).entity(jsonResponse).build();
        }
        else{
            LocalDate birthDate = sellerRequest.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthDate, LocalDate.now()).getYears();

            if (age < 18 || sellerRequest.bio.isEmpty() || sellerRequest.name.isEmpty()){

                String jsonResponse = "{\n\tcode: INVALID_PARAM,\n\tdescription: un parametre (URL, header, JSON, etc.) est invalide (vide, negatif, trop long. etc.)\n}";

                response = Response.status(400).entity(jsonResponse).build();
            }
            else{
                response = Response.status(201).header("Location", url).build();
            }
        }
        return response;
    }
}
