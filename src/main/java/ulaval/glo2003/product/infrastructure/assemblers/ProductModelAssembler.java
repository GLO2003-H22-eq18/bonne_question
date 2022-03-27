package ulaval.glo2003.product.infrastructure.assemblers;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.infrastructure.models.OfferModel;
import ulaval.glo2003.product.infrastructure.models.ProductModel;

import java.util.*;
import java.util.stream.Collectors;

public class ProductModelAssembler {

    private final OfferModelAssembler offerModelAssembler;

    public ProductModelAssembler(OfferModelAssembler offerModelAssembler) {
        this.offerModelAssembler = offerModelAssembler;
    }

    public ProductModel createProductModel(Product product) {
        String id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = ProductCategory.toStringList(product.getCategories());
        String sellerId = product.getSellerId();
        String sellerName = product.getSellerName();
        Integer count = product.getCount();

        // TODO: get offers from product
        List<OfferModel> offers = new ArrayList<>();

        return new ProductModel(id, title, description, createdAt, suggestedPrice, categories, sellerId, sellerName, offers);
    }

    public Product createProduct(ProductModel productModel) {
        String id = productModel.getId();
        String title = productModel.getTitle();
        String description = productModel.getDescription();
        String createdAt = productModel.getCreatedAt();
        Double suggestedPrice = productModel.getSuggestedPrice();
        List<ProductCategory> categories = ProductCategory.toCategoriesList(productModel.getCategories());
        String sellerId = productModel.getSellerId();
        String sellerName = productModel.getSellerName();
        Integer count = productModel.getCount();

        List<Offer> offers;
        if (productModel.getOffers() != null) {
            offers = productModel.getOffers().stream().map(offerModelAssembler::createOffer).collect(Collectors.toList());
        } else {
            offers = new ArrayList<>();
        }

        // TODO: add offers to Product constructor and pass them here
        return new Product(id, title, description, suggestedPrice, categories, sellerId, sellerName);
    }
}
