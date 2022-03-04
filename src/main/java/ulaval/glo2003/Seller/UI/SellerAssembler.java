package ulaval.glo2003.Seller.UI;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Seller.Domain.Seller;

public class SellerAssembler {

    private final SellerProductAssembler sellerProductAssembler;

    public SellerAssembler(SellerProductAssembler sellerProductAssembler) {
        this.sellerProductAssembler = sellerProductAssembler;
    }

    public SellerResponse createSellerResponse(Seller seller) {
        String id = seller.getId();
        String createdAt = seller.getCreatedAt().toString();
        String name = seller.getName();
        String bio = seller.getBio();
        List<Product> products = seller.getProducts();

        List<SellerProductResponse> productsResponse = products.stream()
                .map(sellerProductAssembler::createSellerProductResponse)
                .collect(Collectors.toList());

        return new SellerResponse(id, createdAt, name, bio, productsResponse);
    }
}
