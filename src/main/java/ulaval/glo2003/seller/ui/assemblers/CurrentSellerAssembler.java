package ulaval.glo2003.seller.ui.assemblers;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;

public class CurrentSellerAssembler {

    private final CurrentSellerProductAssembler currentSellerProductAssembler;

    public CurrentSellerAssembler() {
        currentSellerProductAssembler = new CurrentSellerProductAssembler();
    }

    public CurrentSellerResponse createCurrentSellerResponse(Seller seller) {
        String id = seller.getId().toString();
        String name = seller.getName();
        String createdAt = seller.getCreatedAt().toString();
        String bio = seller.getBio();
        String birthDate = seller.getBirthDate().toString();
        List<Product> products = seller.getProducts();

        List<CurrentSellerProductResponse> productsResponse = products.stream()
                .map(currentSellerProductAssembler::createCurrentSellerProductResponse)
                .collect(Collectors.toList());

        return new CurrentSellerResponse(id, name, createdAt, bio, birthDate, productsResponse);
    }

    public CurrentSellerResponse createCurrentSellerViewsResponse(Seller seller) {
        String id = seller.getId().toString();
        String name = seller.getName();
        String createdAt = seller.getCreatedAt().toString();
        String bio = seller.getBio();
        String birthDate = seller.getBirthDate().toString();
        List<Product> products = seller.getProducts();

        List<CurrentSellerProductResponse> productsResponse = products.stream()
                .map(currentSellerProductAssembler::createCurrentSellerProductViewsResponse)
                .collect(Collectors.toList());

        return new CurrentSellerResponse(id, name, createdAt, bio, birthDate, productsResponse);
    }
}
