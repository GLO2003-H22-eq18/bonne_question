package ulaval.glo2003.product.infrastructure.repository;

import com.mongodb.*;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import dev.morphia.query.experimental.updates.UpdateOperators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bson.types.ObjectId;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.infrastructure.assemblers.OfferModelAssembler;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.infrastructure.models.OfferModel;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;

import static dev.morphia.query.experimental.filters.Filters.eq;

public class MongoProductsRepository implements ProductRepository {

    private final Datastore datastore;
    private final OfferModelAssembler offerModelAssembler;
    private final ProductModelAssembler productModelAssembler;

    public MongoProductsRepository(ApplicationContext applicationContext, OfferModelAssembler offerModelAssembler, ProductModelAssembler productModelAssembler) {

        this.offerModelAssembler = offerModelAssembler;
        this.productModelAssembler = productModelAssembler;
        String mongodbUri = applicationContext.getConnectionString();
        ConnectionString connectionString = new ConnectionString(mongodbUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.applySettings(
                        ClusterSettings.builder().serverSelectionTimeout(1000, TimeUnit.MILLISECONDS).build())
                )
                .applyToConnectionPoolSettings(builder -> builder.applySettings(
                        ConnectionPoolSettings.builder().maxConnectionIdleTime(1000, TimeUnit.MILLISECONDS).build()
                ))
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

//        MongoDatabase mongoDatabase = mongoClient.getDatabase(applicationContext.getDatabase());
        this.datastore = Morphia.createDatastore(mongoClient, applicationContext.getDatabase());
        this.datastore.getMapper().mapPackage("ulaval.glo2003.product.infrastructure");
    }

    @Override
    public void save(Product product) {
        ProductModel productModel = productModelAssembler.createProductModel(product);
        datastore.save(productModel);
    }

    @Override
    public Product findById(ObjectId productId) {
        ProductModel productModel = datastore.find(ProductModel.class).filter(eq("_id", productId)).first();

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
            filteredProductsList = getProductsFilterByTitle(filteredProductsList, filteredProductRequest.title);
        }

        if (!filteredProductRequest.categories.isEmpty()) {
            filteredProductsList = getProductsFilterByCategories(filteredProductsList, filteredProductRequest.categories);
        }

        if (filteredProductRequest.minPrice != null) {
            filteredProductsList =
                    getMinPriceFilteredProducts(filteredProductsList, filteredProductRequest.minPrice);
        }

        if (filteredProductRequest.maxPrice != null) {
            filteredProductsList =
                    getMaxPriceFilteredProducts(filteredProductsList, filteredProductRequest.maxPrice);
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

    public void updateOffer(Offer offer, String productId) {
        ProductModel productModel = datastore.find(ProductModel.class)
                .filter(eq("_id", productId))
                .first();

        if (productModel == null) {
            throw new ProductNotFoundException();
        }

        OfferModel offerModel = offerModelAssembler.createOfferModel(offer);
        List<OfferModel> offers = Objects.requireNonNullElse(productModel.getOffers(), new ArrayList<>());
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

    public int getNextId() {
//        List<ProductModel> products = datastore.find(ProductModel.class).stream().collect(Collectors.toList());
//        int nextId = Integer.parseInt(products.get(0).getId());
//
//        for (ProductModel product : products) {
//            int id = Integer.parseInt(product.getId());
//
//            if (id > nextId) {
//                nextId = id;
//            }
//        }

        return 0;
    }
}
