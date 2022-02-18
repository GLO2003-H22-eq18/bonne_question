package ulaval.glo2003.Product.UI;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;

@Path("/products")
public class ProductResource {
    private static final ProductAssembler productAssembler = new ProductAssembler();

    @POST
    public Response postProduct(ProductRequest productRequest,
                                @HeaderParam("X-Seller-Id") String sellerId,
                                @Context UriInfo uri){

        ProductFactory productFactory = new ProductFactory();
        Product myProduct = productFactory.create(productRequest, sellerId);
        // TODO : ProductRepository.save(myProduct);

        return Response.status(201).header("Location", uri.getPath() + "/" + myProduct.getId()).build();
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
