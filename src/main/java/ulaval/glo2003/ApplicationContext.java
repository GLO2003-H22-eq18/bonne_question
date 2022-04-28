package ulaval.glo2003;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ApplicationContext {
    private enum ApplicationMode {
        Staging, Production, Dev
    }

    private ApplicationMode getApplicationMode() {
        String mode = System.getenv("MODE");

        if (mode == null || mode.equals("DEV")) {
            return ApplicationMode.Dev;
        } else if (mode.equals("PROD")) {
            return ApplicationMode.Production;
        } else if (mode.equals("STAGING")) {
            return ApplicationMode.Staging;
        }

        throw new UnsupportedOperationException("Mode " + mode + " not handled");
    }

    private String getDatabase() {
        ApplicationMode mode = getApplicationMode();

        if (mode.equals(ApplicationMode.Production)) {
            return "floppa-prod";
        } else if (mode.equals(ApplicationMode.Staging)) {
            return "floppa-staging";
        } else {
            return "floppa-dev";
        }
    }

    private String getConnectionString() {
        return Objects.requireNonNullElse(System.getenv("MONGO_CONNECTION"),
                        "mongodb://localhost:27017/?w=majority&readPreference=primary&appname=MongoDB%20Compass&retryWrites=true&directConnection=true&ssl=false");
    }

    public Datastore getDatastore() {
        String mongodbUri = getConnectionString();

        ConnectionString connectionString = new ConnectionString(mongodbUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.applySettings(
                        ClusterSettings.builder()
                                .serverSelectionTimeout(5000, TimeUnit.MILLISECONDS).build())
                )
                .applyToConnectionPoolSettings(builder -> builder.applySettings(
                        ConnectionPoolSettings.builder()
                                .maxConnectionIdleTime(5000, TimeUnit.MILLISECONDS).build()
                ))
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        return Morphia.createDatastore(mongoClient, getDatabase());
    }
}
