package ulaval.glo2003;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

  @GET
  public Response getHealth() {
    return Response.ok().build();
  }
}
