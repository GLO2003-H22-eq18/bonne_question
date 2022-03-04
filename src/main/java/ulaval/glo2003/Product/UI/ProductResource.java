package ulaval.glo2003.Product.UI;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductFactory;
import ulaval.glo2003.Product.Domain.ProductRepository;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerRepository;

@Path("/products")
public class ProductResource {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;
    private final ProductAssembler productAssembler;

    public ProductResource(
            SellerRepository sellerRepository,
            ProductRepository productRepository,
            ProductFactory productFactory,
            ProductAssembler productAssembler) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.productFactory = productFactory;
        this.productAssembler = productAssembler;
    }

    @POST
    public Response postProduct(
            ProductRequest productRequest,
            @HeaderParam("X-Seller-Id") String sellerId,
            @Context UriInfo uri) {

        Seller productSeller = sellerRepository.findById(sellerId);
        Product myProduct = productFactory.create(productSeller, productRequest);
        productSeller.addProduct(myProduct);
        productRepository.save(myProduct);

        return Response.status(201)
                .header("Location", uri.getPath() + "/" + myProduct.getId())
                .build();
    }

    @GET
    @Path("/{productId}")
    public Response getProduct(@PathParam("productId") String productId) {
        Product product = productRepository.findById(productId);

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        return Response.status(200).entity(productResponse).build();
    }

    @GET
    public Response getFilteredProducts(
            @QueryParam("sellerId") String sellerId,
            @QueryParam("title") String title,
            @QueryParam("categories") List<String> categories,
            @QueryParam("minPrice") String minPrice,
            @QueryParam("maxPrice") String maxPrice) {

        ProductUtil.checkFilteredProductInvalidParam(sellerId, title, categories, minPrice, maxPrice);

        List<Product> filteredProducts = productRepository
                .getFilteredProducts(sellerId, title, categories, minPrice, maxPrice);

        List<ProductResponse> filteredProductsResponseList = filteredProducts
                .stream()
                .map(productAssembler::createProductResponse)
                .collect(Collectors.toList());

        FilteredProductsResponse filteredProductsResponse =
                new FilteredProductsResponse(filteredProductsResponseList);

        return Response.status(200).entity(filteredProductsResponse).build();
    }
}
