package ulaval.glo2003.product.infrastructure.assemblers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.infrastructure.models.OfferModel;
import ulaval.glo2003.product.infrastructure.models.ProductModel;

public class ProductModelAssembler {

    private final OfferModelAssembler offerModelAssembler;

    public ProductModelAssembler(OfferModelAssembler offerModelAssembler) {
        this.offerModelAssembler = offerModelAssembler;
    }

    public ProductModel createProductModel(Product product) {
        ObjectId id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = ProductCategory.toStringList(product.getCategories());
        ObjectId sellerId = product.getSellerId();
        String sellerName = product.getSellerName();

        List<OfferModel> offers = product.getOffers()
                .stream()
                .map(offerModelAssembler::createOfferModel)
                .collect(Collectors.toList());

        return new ProductModel(title, description, createdAt, suggestedPrice, categories,
                sellerId, sellerName, id, offers);
    }

    public Product createProduct(ProductModel productModel) {
        ObjectId id = productModel.getId();
        String title = productModel.getTitle();
        String description = productModel.getDescription();
        OffsetDateTime createdAt = OffsetDateTime.parse(productModel.getCreatedAt());
        Double suggestedPrice = productModel.getSuggestedPrice();
        ObjectId sellerId = productModel.getSellerId();
        String sellerName = productModel.getSellerName();
        Integer count = productModel.getCount();

        List<ProductCategory> categories;
        if (productModel.getCategories() != null) {
            categories = ProductCategory.toCategoriesList(productModel.getCategories());
        } else {
            categories = new ArrayList<>();
        }

        List<Offer> offers;
        if (productModel.getOffers() != null) {
            offers = productModel.getOffers().stream().map(offerModelAssembler::createOffer)
                    .collect(Collectors.toList());
        } else {
            offers = new ArrayList<>();
        }

        return new Product(title, description, suggestedPrice, categories, sellerId,
                sellerName, id, offers, createdAt);
    }
}
