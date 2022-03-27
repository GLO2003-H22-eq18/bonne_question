package ulaval.glo2003.product.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.exceptions.SellerNotFoundException;
import ulaval.glo2003.seller.infrastructure.assemblers.SellerModelAssembler;
import ulaval.glo2003.seller.infrastructure.models.SellerModel;

import static dev.morphia.query.experimental.filters.Filters.eq;

public class MongoProductsRepository implements ProductRepository {

    private final MongoDatabase mongoDatabase;
    private final Datastore datastore;
    private final ProductModelAssembler productModelAssembler;

    public MongoProductsRepository(String database, ProductModelAssembler productModelAssembler) {

        this.productModelAssembler = productModelAssembler;
        String mongodbUri = System.getenv("MONGODB_URI");
        ConnectionString connectionString = new ConnectionString(mongodbUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        this.mongoDatabase = mongoClient.getDatabase(database);
        this.datastore = Morphia.createDatastore(mongoClient, database);
        this.datastore.getMapper().mapPackage("ulaval.glo2003.product.infrastructure");
    }

    @Override
    public void save(Product product) {
        ProductModel productModel = productModelAssembler.createProductModel(product);
        datastore.save(productModel);
    }

    @Override
    public Product findById(String productId) {
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

    public List<Product> getProductsFilterBySellerId(String sellerId) {
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
}
