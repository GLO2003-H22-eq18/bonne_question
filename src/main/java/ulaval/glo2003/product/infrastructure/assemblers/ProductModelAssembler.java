package ulaval.glo2003.product.infrastructure.assemblers;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.infrastructure.models.OfferModel;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.product.infrastructure.models.ViewModel;

public class ProductModelAssembler {

    private final OfferModelAssembler offerModelAssembler;
    private final ViewModelAssembler viewModelAssembler;

    public ProductModelAssembler(OfferModelAssembler offerModelAssembler,
                                 ViewModelAssembler viewModelAssembler) {
        this.offerModelAssembler = offerModelAssembler;
        this.viewModelAssembler = viewModelAssembler;
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

        List<ViewModel> views = product.getViews()
                .stream()
                .map(viewModelAssembler::createViewModel)
                .collect(Collectors.toList());

        return new ProductModel(title, description, createdAt, suggestedPrice, categories,
                sellerId, sellerName, id, offers, views);
    }

    public Product createProduct(ProductModel productModel) {
        final ObjectId id = productModel.getId();
        final String title = productModel.getTitle();
        final String description = productModel.getDescription();
        final OffsetDateTime createdAt = OffsetDateTime.parse(productModel.getCreatedAt());
        final Double suggestedPrice = productModel.getSuggestedPrice();
        final ObjectId sellerId = productModel.getSellerId();
        final String sellerName = productModel.getSellerName();

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

        List<View> views;
        if (productModel.getViews() != null) {
            views = productModel.getViews().stream().map(viewModelAssembler::createView)
                    .collect(Collectors.toList());
        } else {
            views = new ArrayList<>();
        }

        return new Product(title, description, suggestedPrice, categories, sellerId,
                sellerName, id, offers, views, createdAt);
    }
}
