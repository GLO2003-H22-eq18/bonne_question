package ulaval.glo2003.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class SellerNotFoundExceptionMapper implements ExceptionMapper<SellerNotFoundException> {

    @Override
    public Response toResponse(SellerNotFoundException e) {
        String jsonResponse = "{\n\tcode: ITEM_NOT_FOUND,\n\tdescription: " + e.getMessage() + "\n}";
        return Response.status(404).entity(jsonResponse).build();
    }
}
