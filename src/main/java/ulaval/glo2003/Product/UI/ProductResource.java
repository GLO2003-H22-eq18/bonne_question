package ulaval.glo2003.Product.UI;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;

@Path("/products")
public class ProductResource {
    private final ProductAssembler productAssembler;
    private final ProductFactory productFactory;
    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository, ProductFactory productFactory, ProductAssembler productAssembler) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
        this.productAssembler = productAssembler;
    }

    @POST
    public Response postProduct(ProductRequest productRequest,
                                @HeaderParam("X-Seller-Id") String sellerId,
                                @Context UriInfo uri) {

        Product myProduct = productFactory.create(productRequest, sellerId);
        productRepository.save(myProduct);

        return Response.status(201).header("Location", uri.getPath() + "/" + myProduct.getId()).build();
    }

    @GET
    @Path("/{productId}")
    public Response getProduct(@PathParam("productId") String productId) {
        Product product = productRepository.find(productId);

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        return Response.status(200).entity(productResponse).build();
    }
}
