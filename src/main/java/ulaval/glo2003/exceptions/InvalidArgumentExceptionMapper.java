package ulaval.glo2003.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvalidArgumentExceptionMapper implements ExceptionMapper<InvalidArgumentException> {

    @Override
    public Response toResponse(InvalidArgumentException e) {
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse =
                new ErrorResponse(ErrorCode.INVALID_PARAMETER, errorDescription);

        return Response.status(400).entity(errorResponse).build();
    }
}
