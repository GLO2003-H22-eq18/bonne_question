package ulaval.glo2003.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class MissingArgumentExceptionMapper implements ExceptionMapper<MissingArgumentException> {

    @Override
    public Response toResponse(MissingArgumentException e) {
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse =
                new ErrorResponse(ErrorCode.MISSING_PARAMETER, errorDescription);

        return Response.status(400).entity(errorResponse).build();
    }
}
