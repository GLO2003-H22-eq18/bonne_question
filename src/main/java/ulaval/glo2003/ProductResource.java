package ulaval.glo2003;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/products")
public class ProductResource {

    @POST
    public Response postProduct(ProductRequest productRequest,
                                @HeaderParam("X-Seller-Id") String sellerId,
                                @Context UriInfo uri){

        ProductFactory productFactory = new ProductFactory();
        Product myProduct = productFactory.create(productRequest, sellerId);
        // TODO : ProductRepository.save(myProduct);

        return Response.status(201).header("Location", uri.getPath() + "/" + myProduct.id).build();
    }
}
