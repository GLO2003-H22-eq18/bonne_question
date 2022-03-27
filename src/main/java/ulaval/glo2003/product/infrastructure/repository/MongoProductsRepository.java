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
        System.out.println(mongodbUri);
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
        return null;
    }

    public List<Product> getProductsFilterBySellerId(String sellerId) {
        return null;
    }

    private List<Product> getProductsFilterByTitle(
            List<Product> filteredProductsList, String title) {
        return null;
    }

    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<ProductCategory> categories) {
        return null;
    }

    public List<Product> getMinPriceFilteredProducts(
            List<Product> filteredProductsList, Double minPrice) {
        return null;
    }

    public List<Product> getMaxPriceFilteredProducts(
            List<Product> filteredProductsList, Double maxPrice) {
        return null;
    }
}
