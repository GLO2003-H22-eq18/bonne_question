package ulaval.glo2003.product.infrastructure.repository;

import static dev.morphia.query.experimental.filters.Filters.eq;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.updates.UpdateOperators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.infrastructure.assemblers.OfferModelAssembler;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.infrastructure.assemblers.ViewModelAssembler;
import ulaval.glo2003.product.infrastructure.models.OfferModel;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.product.infrastructure.models.ViewModel;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;

public class MongoProductsRepository implements ProductRepository {

    private final Datastore datastore;
    private final OfferModelAssembler offerModelAssembler;
    private final ViewModelAssembler viewModelAssembler;
    private final ProductModelAssembler productModelAssembler;

    public MongoProductsRepository(ApplicationContext applicationContext,
                                   OfferModelAssembler offerModelAssembler,
                                   ViewModelAssembler viewModelAssembler,
                                   ProductModelAssembler productModelAssembler) {

        this.offerModelAssembler = offerModelAssembler;
        this.viewModelAssembler = viewModelAssembler;
        this.productModelAssembler = productModelAssembler;
        this.datastore = applicationContext.getDatastore();
        this.datastore.getMapper().mapPackage("ulaval.glo2003.product.infrastructure");
    }

    @Override
    public void save(Product product) {
        ProductModel productModel = productModelAssembler.createProductModel(product);
        datastore.save(productModel);
    }

    @Override
    public Product findById(ObjectId productId) {
        ProductModel productModel =
                datastore.find(ProductModel.class).filter(eq("_id", productId)).first();

        if (productModel == null) {
            throw new ProductNotFoundException();
        }

        return productModelAssembler.createProduct(productModel);
    }

    @Override
    public List<Product> getFilteredProducts(
            FilteredProductRequest filteredProductRequest) {
        List<Product> filteredProductsList;
        if (filteredProductRequest.sellerId != null) {
            filteredProductsList = getProductsFilterBySellerId(filteredProductRequest.sellerId);
        } else {
            filteredProductsList = datastore.find(ProductModel.class)
                    .stream()
                    .map(productModelAssembler::createProduct)
                    .collect(Collectors.toList());
        }

        if (filteredProductRequest.title != null) {
            filteredProductsList =
                    getProductsFilterByTitle(filteredProductsList, filteredProductRequest.title);
        }

        if (!filteredProductRequest.categories.isEmpty()) {
            filteredProductsList = getProductsFilterByCategories(filteredProductsList,
                    filteredProductRequest.categories);
        }

        if (filteredProductRequest.minPrice != null) {
            filteredProductsList =
                    getMinPriceFilteredProducts(filteredProductsList,
                            filteredProductRequest.minPrice);
        }

        if (filteredProductRequest.maxPrice != null) {
            filteredProductsList =
                    getMaxPriceFilteredProducts(filteredProductsList,
                            filteredProductRequest.maxPrice);
        }

        return filteredProductsList;
    }

    public List<Product> getProductsFilterBySellerId(ObjectId sellerId) {
        return datastore.find(ProductModel.class)
                .filter((eq("sellerId", sellerId))).stream()
                .map(productModelAssembler::createProduct)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFilterByTitle(
            List<Product> filteredProductsList, String title) {
        final String lowerCaseTitle = title.toLowerCase();
        return filteredProductsList.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<ProductCategory> categories) {
        return filteredProductsList.stream()
                .filter(
                        product ->
                                !Collections.disjoint(product.getCategories(), categories))
                .collect(Collectors.toList());
    }

    public List<Product> getMinPriceFilteredProducts(
            List<Product> filteredProductsList, Double minPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> getMaxPriceFilteredProducts(
            List<Product> filteredProductsList, Double maxPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public void updateOffer(Offer offer, ObjectId productId) {
        ProductModel productModel = datastore.find(ProductModel.class)
                .filter(eq("_id", productId))
                .first();

        if (productModel == null) {
            throw new ProductNotFoundException();
        }

        OfferModel offerModel = offerModelAssembler.createOfferModel(offer);
        List<OfferModel> offers =
                Objects.requireNonNullElse(productModel.getOffers(), new ArrayList<>());
        offers.add(offerModel);


        datastore.find(ProductModel.class)
                .filter(eq("_id", productId))
                .update(
                        UpdateOperators.set(
                                "offers",
                                offers
                        )
                )
                .execute();
    }

    @Override
    public void updateView(View view, ObjectId productId) {
        ProductModel productModel = datastore.find(ProductModel.class)
                .filter(eq("_id", productId))
                .first();

        if (productModel == null) {
            throw new ProductNotFoundException();
        }

        ViewModel viewModel = viewModelAssembler.createViewModel(view);
        List<ViewModel> views =
                Objects.requireNonNullElse(productModel.getViews(), new ArrayList<>());
        views.add(viewModel);

        datastore.find(ProductModel.class)
                .filter(eq("_id", productId))
                .update(
                        UpdateOperators.set(
                                "views",
                                views
                        )
                )
                .execute();
    }
}
