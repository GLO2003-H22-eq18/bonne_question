package ulaval.glo2003.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    @Override
    public Response toResponse(ItemNotFoundException e) {
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.ITEM_NOT_FOUND, errorDescription);

        return Response.status(404).entity(errorResponse).build();
    }
}
