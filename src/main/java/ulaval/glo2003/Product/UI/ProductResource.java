package ulaval.glo2003.Product.UI;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductResource {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public ProductResource( SellerRepository sellerRepository,
                            ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @POST
    public Response postProduct(ProductRequest productRequest,
                                @HeaderParam("X-Seller-Id") String sellerId,
                                @Context UriInfo uri) {

        Seller productSeller = sellerRepository.find(sellerId);
        Product myProduct = ProductFactory.create(productSeller, productRequest);
        productSeller.addProduct(myProduct);
        productRepository.save(myProduct);

        return Response.status(201).header("Location", uri.getPath() + "/" + myProduct.getId()).build();
    }

    @GET
    @Path("/{productId}")
    public Response getProduct(@PathParam("productId") String productId) {
        Product product = productRepository.find(productId);

        ProductResponse productResponse = ProductAssembler.createProductResponse(product);

        return Response.status(200).entity(productResponse).build();
    }

    @GET
    public Response getFilteredProducts(@QueryParam("sellerId") String sellerId,
            @QueryParam("title") String title,
            @QueryParam("categories") List<String> categories,
            @QueryParam("minPrice") Double minPrice,
            @QueryParam("maxPrice") Double maxPrice) {

        List<Product> filteredProducts = productRepository.getFilteredProducts(sellerId, title, categories, minPrice, maxPrice);

        List<ProductResponse> filteredProductsResponseList = filteredProducts
                .stream()
                .map(ProductAssembler::createProductResponse)
                .collect(Collectors.toList());

        FilteredProductsResponse filteredProductsResponse = new FilteredProductsResponse(filteredProductsResponseList);

        return Response.status(200).entity(filteredProductsResponse).build();
    }
}
