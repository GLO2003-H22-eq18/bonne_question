package ulaval.glo2003.product.ui;

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

import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferFactory;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.ui.assemblers.OfferRequestAssembler;
import ulaval.glo2003.product.ui.assemblers.ProductAssembler;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;
import ulaval.glo2003.product.ui.requests.OfferRequest;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.product.ui.responses.FilteredProductsResponse;
import ulaval.glo2003.product.ui.responses.ProductResponse;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;


@Path("/products")
public class ProductResource {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;
    private final ProductAssembler productAssembler;
    private final OfferFactory offerFactory;
    private final OfferRequestAssembler offerRequestAssembler;

    public ProductResource(
            SellerRepository sellerRepository,
            ProductRepository productRepository,
            ProductFactory productFactory,
            ProductAssembler productAssembler,
            OfferFactory offerFactory) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.productFactory = productFactory;
        this.productAssembler = productAssembler;
        this.offerFactory = offerFactory;
        this.offerRequestAssembler = new OfferRequestAssembler();
    }

    @POST
    public Response postProduct(
            ProductRequest productRequest,
            @HeaderParam("X-Seller-Id") String sellerId,
            @Context UriInfo uri) {

        productAssembler.checkProductRequestMissingParams(productRequest);
        Seller productSeller = sellerRepository.findById(new ObjectId(sellerId));
        Product myProduct = productFactory.create(productSeller, productRequest);
        productSeller.addProduct(myProduct);
        productRepository.save(myProduct);
        sellerRepository.updateSeller(myProduct);

        return Response.status(201)
                .header("Location", uri.getPath() + "/" + myProduct.getId())
                .build();
    }

    @GET
    @Path("/{productId}")
    public Response getProduct(@PathParam("productId") String productId) {
        Product product = productRepository.findById(new ObjectId(productId));

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

        FilteredProductRequest filteredProductRequest =
                productAssembler.createFilteredProductRequest(sellerId, title, categories, minPrice,
                        maxPrice);

        List<Product> filteredProducts = productRepository
                .getFilteredProducts(filteredProductRequest);

        List<ProductResponse> filteredProductsResponseList = filteredProducts
                .stream()
                .map(productAssembler::createProductResponse)
                .collect(Collectors.toList());

        FilteredProductsResponse filteredProductsResponse =
                new FilteredProductsResponse(filteredProductsResponseList);

        return Response.status(200).entity(filteredProductsResponse).build();
    }

    @POST
    @Path("/{productId}/offers")
    public Response postOffer(
            OfferRequest offerRequest,
            @PathParam("productId") String productId) {

        offerRequestAssembler.checkOfferRequestMissingParams(offerRequest);
        Product product = productRepository.findById(new ObjectId(productId));
        Offer myOffer = offerFactory.create(product.getSuggestedPrice(), offerRequest);
        product.addOffer(myOffer);
        productRepository.updateOffer(myOffer, productId);


        return Response.status(200).build();
    }
}
