package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/products")
public class ProductResource {
    private static final ProductAssembler productAssembler = new ProductAssembler();

    @POST
    public Response postProduct(ProductRequest productRequest,
                                @HeaderParam("X-Seller-Id") String sellerId,
                                @Context UriInfo uri){

        Product myProduct = ProductFactory.create(productRequest, sellerId);
        // TODO : ProductRepository.save(myProduct);

        return Response.status(201).header("Location", uri.getPath() + "/" + myProduct.id).build();
    }

    @GET
    @Path("/{productId}")
    public Response getProduct(@PathParam("productId") String productId) {
        // Product product = ...

        // ProductResponse productResponse = productAssembler.createProductResponse(product);

        // return Response.status(200).entity(sellerResponse).build();

        return Response.status(200).build();
    }
}
