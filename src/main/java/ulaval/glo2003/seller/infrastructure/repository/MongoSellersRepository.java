package ulaval.glo2003.seller.infrastructure.repository;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.FindOptions;
import dev.morphia.query.MorphiaCursor;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.updates.UpdateOperator;
import dev.morphia.query.experimental.updates.UpdateOperators;
import java.util.ArrayList;
import java.util.Objects;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.infrastructure.models.ProductModel;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.exceptions.SellerNotFoundException;
import ulaval.glo2003.seller.infrastructure.assemblers.SellerModelAssembler;
import ulaval.glo2003.seller.infrastructure.models.SellerModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.morphia.query.experimental.filters.Filters.*;

public class MongoSellersRepository implements SellerRepository {
    private final MongoDatabase mongoDatabase;
    private final Datastore datastore;
    private final SellerModelAssembler sellerModelAssembler;
    private final ProductModelAssembler productModelAssembler;

    public MongoSellersRepository(String database, SellerModelAssembler sellerModelAssembler, ProductModelAssembler productModelAssembler) {

        this.sellerModelAssembler = sellerModelAssembler;
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
        this.datastore.getMapper().mapPackage("ulaval.glo2003.seller.infrastructure");
    }

    @Override
    public Seller findById(String sellerId) {
        if (sellerId == null) {
            throw new SellerNotFoundException();
        }

        SellerModel sellerModel = datastore.find(SellerModel.class).filter(eq("_id", sellerId)).first();

        if (sellerModel == null) {
            throw new SellerNotFoundException();
        }

        return sellerModelAssembler.createSeller(sellerModel);
    }

    @Override
    public void save(Seller seller) {
        SellerModel sellerModel = sellerModelAssembler.createSellerModel(seller);
        datastore.save(sellerModel);
    }

    @Override
    public Map<String, Seller> getSellers() {
        List<Seller> sellersList =
                datastore.find(SellerModel.class)
                        .stream()
                        .map(sellerModelAssembler::createSeller)
                        .collect(Collectors.toList());

        Map<String, Seller> sellers = new HashMap<>();
        sellersList.forEach(seller -> sellers.put(seller.getId(), seller));

        return sellers;
    }

    public void updateSeller(Product product) {
        SellerModel sellerModel = datastore.find(SellerModel.class)
                .filter(eq("_id", product.getSellerId()))
                .first();

        if (sellerModel == null) {
            throw new SellerNotFoundException();
        }

        ProductModel productModel = productModelAssembler.createProductModel(product);
        List<ProductModel> products = Objects.requireNonNullElse(sellerModel.getProducts(), new ArrayList<>());
        products.add(productModel);

        datastore.find(SellerModel.class)
                .filter(eq("_id", product.getSellerId()))
                .update(
                    UpdateOperators.set(
                            "products",
                            products
                    )
                )
                .execute();
    }
}
