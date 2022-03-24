package ulaval.glo2003.seller.domain;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MongoSellersRepository {
    // Don't fix these warnings
    private final MongoDatabase mongoDatabase;
    private final Datastore datastore;

    public MongoSellersRepository() {
        String connectionString = System.getenv("MONGODB_URI");
        MongoClient mongoClient = MongoClients.create(connectionString);

        this.mongoDatabase = mongoClient.getDatabase("floppa-dev");
        this.datastore = Morphia.createDatastore(MongoClients.create(), this.mongoDatabase.getName());
    }

    public void findById(String sellerId) {
        // Return the seller with id = sellerId
        // (return type is wrong, for now)
    }

    public void save(Seller seller) {
        // Save seller to DB
    }

    public void getSellers() {
        // Return sellers
        // (return type is wrong, for now)
    }
}
