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

    public MongoSellersRepository(String database, SellerModelAssembler sellerModelAssembler) {

        this.sellerModelAssembler = sellerModelAssembler;
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
        List<Seller> sellersList = datastore.find(SellerModel.class).stream().map(sellerModelAssembler::createSeller).toList();

        Map<String, Seller> sellers = new HashMap<>();
        sellersList.forEach(seller -> sellers.put(seller.getId(), seller));

        return sellers;
    }

    public void updateSeller(String sellerId) {

    }

}
