package ulaval.glo2003.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class MissingArgumentExceptionMapper implements ExceptionMapper<MissingArgumentException> {

    @Override
    public Response toResponse(MissingArgumentException e) {
        String jsonResponse = "{\n\tcode: MISSING_PARAM,\n\tdescription: " + e.getMessage() + "\n}";
        return Response.status(400).entity(jsonResponse).build();
    }
}
