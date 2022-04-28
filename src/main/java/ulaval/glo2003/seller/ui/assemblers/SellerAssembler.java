package ulaval.glo2003.seller.ui.assemblers;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.responses.SellerProductResponse;
import ulaval.glo2003.seller.ui.responses.SellerResponse;

public class SellerAssembler {

    private final SellerProductAssembler sellerProductAssembler;

    public SellerAssembler() {
        sellerProductAssembler = new SellerProductAssembler();
    }

    public SellerResponse createSellerResponse(Seller seller) {
        String id = seller.getId().toString();
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
