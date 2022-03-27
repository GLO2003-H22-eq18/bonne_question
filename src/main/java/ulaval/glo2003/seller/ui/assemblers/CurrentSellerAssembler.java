package ulaval.glo2003.seller.ui.assemblers;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;

public class CurrentSellerAssembler {

    public CurrentSellerResponse createCurrentSellerResponse(Seller seller) {

        String id = seller.getId();
        String name = seller.getName();
        String createdAt = seller.getCreatedAt().toString();
        String bio = seller.getBio();
        String birthDate = seller.getBirthDate().toString();
        List<Product> products = seller.getProducts();

        List<CurrentSellerProductResponse> productsResponse = products.stream()
                .map(CurrentSellerProductAssembler::createCurrentSellerProductResponse)
                .collect(Collectors.toList());

        return new CurrentSellerResponse(id, name, createdAt, bio, birthDate, productsResponse);
    }
}
