package ulaval.glo2003.seller.infrastructure.assemblers;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.infrastructure.models.SellerModel;

public class SellerModelAssembler {

    private final ProductModelAssembler productModelAssembler;

    public SellerModelAssembler(ProductModelAssembler productModelAssembler) {
        this.productModelAssembler = productModelAssembler;
    }

    public SellerModel createSellerModel(Seller seller) {
        ObjectId id = seller.getId();
        String createdAt = seller.getCreatedAt().toString();
        String birthDate = seller.getBirthDate().toString();
        String name = seller.getName().toString();
        String bio = seller.getBio().toString();
        List<ProductModel> products =
                seller.getProducts().stream().map(productModelAssembler::createProductModel)
                        .collect(Collectors.toList());

        return new SellerModel(id, createdAt, birthDate, name, bio, products);
    }

    public Seller createSeller(SellerModel sellerModel) {
        ObjectId id = sellerModel.getId();
        OffsetDateTime createdAt = OffsetDateTime.parse(sellerModel.getCreatedAt());
        LocalDate birthDate = LocalDate.parse(sellerModel.getBirthDate());
        String name = sellerModel.getName().toString();
        String bio = sellerModel.getBio().toString();

        List<Product> products;
        if (sellerModel.getProducts() != null) {
            products = sellerModel.getProducts().stream().map(productModelAssembler::createProduct)
                    .collect(Collectors.toList());
        } else {
            products = new ArrayList<>();
        }

        return new Seller(id, name, bio, createdAt, birthDate, products);
    }

}
