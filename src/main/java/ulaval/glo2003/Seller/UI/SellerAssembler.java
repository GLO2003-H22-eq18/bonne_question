package ulaval.glo2003.Seller.UI;

import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.UI.ProductResponse;
import ulaval.glo2003.Seller.Domain.Seller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SellerAssembler {

    private final SellerProductAssembler sellerProductAssembler;
    public SellerAssembler(SellerProductAssembler sellerProductAssembler) {
        this.sellerProductAssembler = sellerProductAssembler;
    }

    public SellerResponse createSellerResponse(Seller seller) {
        String id = seller.getId();
        OffsetDateTime createdAt = seller.getCreatedAt();
        String name = seller.getName();
        String bio = seller.getBio();
        List<Product> products = seller.getProducts();

        List<SellerProductResponse> productsResponse = products
                .stream()
                .map(sellerProductAssembler::createSellerProductResponse)
                .collect(Collectors.toList());

        return new SellerResponse(id, createdAt, name, bio, productsResponse);
    }
}
