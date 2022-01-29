package ulaval.glo2003.Exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    @Override
    public Response toResponse(ItemNotFoundException e) {
        String jsonResponse = "{\n\tcode: ITEM_NOT_FOUND,\n\tdescription: " + e.getMessage() + "\n}";
        return Response.status(404).entity(jsonResponse).build();
    }
}
