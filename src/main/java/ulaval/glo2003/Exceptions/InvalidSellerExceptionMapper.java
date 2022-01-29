package ulaval.glo2003.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvalidSellerExceptionMapper implements ExceptionMapper<InvalidArgumentException> {

    @Override
    public Response toResponse(InvalidArgumentException e) {
        String jsonResponse = "{\n\tcode: INVALID_PARAM,\n\tdescription: " + e.getMessage() + "\n}";
        return Response.status(400).entity(jsonResponse).build();

    }
}
