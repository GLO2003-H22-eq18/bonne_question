package ulaval.glo2003.seller.infrastructure;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import static dev.morphia.query.experimental.filters.Filters.eq;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class MongoSellersRepository implements SellerRepository {
    private final MongoDatabase mongoDatabase;
    private final Datastore datastore;

    public MongoSellersRepository(ApplicationContext.ApplicationMode applicationMode) {

        String MONGODB_URI = System.getenv("MONGODB_URI");
        ConnectionString connectionString = new ConnectionString(MONGODB_URI);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);

        if (applicationMode.equals(ApplicationContext.ApplicationMode.Production)) {
            this.mongoDatabase = mongoClient.getDatabase("floppa-prod");
        } else if (applicationMode.equals(ApplicationContext.ApplicationMode.Staging)) {
            this.mongoDatabase = mongoClient.getDatabase("floppa-staging");
        } else {
            this.mongoDatabase = mongoClient.getDatabase("floppa-dev");
        }

        this.datastore = Morphia.createDatastore(mongoClient, "floppa-dev");
        this.datastore.getMapper().mapPackage("ulaval.glo2003.seller.ui");
    }

    @Override
    public Seller findById(String sellerId) {
//        Seller seller;
//        long query = datastore.find(SellerModel.class)
//                .filter(eq("_id", sellerId))
//                .stream().count();
//
//        System.out.println(query);
//        return null;
        return null;
    }

    @Override
    public void save(Seller seller) {
//        SellerModel sellerInMemory = new SellerModel(seller);
//        this.datastore.save(sellerInMemory);
    }

    @Override
    public Map<String, Seller> getSellers() {
//        Query<SellerModel> query = datastore.find(SellerModel.class);
//        HashMap<String, Seller> sellers = new HashMap<>();
//        query.stream().forEach(sellerInMemory -> sellers.put(sellerInMemory.getId(), new Seller("John", "Bio", OffsetDateTime.now(), null, null, 1)));
//
//        return sellers;
        return null;
    }
}
