package ulaval.glo2003.seller.infrastructure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.Map;

public class MongoSellersRepository implements SellerRepository {
    private final MongoDatabase mongoDatabase;
    private final Datastore datastore;

    public MongoSellersRepository(ApplicationContext.ApplicationMode applicationMode) {
        String connectionString = System.getenv("MONGODB_URI");
        MongoClient mongoClient = MongoClients.create(connectionString);

        if (applicationMode.equals(ApplicationContext.ApplicationMode.Production)) {
            this.mongoDatabase = mongoClient.getDatabase("floppa-prod");
        } else if (applicationMode.equals(ApplicationContext.ApplicationMode.Staging)) {
            this.mongoDatabase = mongoClient.getDatabase("floppa-staging");
        } else {
            this.mongoDatabase = mongoClient.getDatabase("floppa-dev");
        }

        this.datastore = Morphia.createDatastore(MongoClients.create(), this.mongoDatabase.getName());
    }

    @Override
    public Seller findById(String sellerId) {
        // Return the seller with id = sellerId
        // (return type is wrong, for now)
    }

    @Override
    public void save(Seller seller) {
        // Save seller to DB
    }

    @Override
    public Map<String, Seller> getSellers() {
        // Return sellers
        // (return type is wrong, for now)
    }
}
