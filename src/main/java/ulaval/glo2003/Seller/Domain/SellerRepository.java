package ulaval.glo2003.Seller.Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SellerRepository {
    private final Map<String, Seller> sellers;

    public SellerRepository() {
        sellers = new HashMap<>();
    }

    public Optional<Seller> find(String sellerId) {
        return Optional.ofNullable(sellers.get(sellerId));
    }

    public void save(Seller seller) {
        String sellerId = seller.getId();
        sellers.put(sellerId, seller);
    }

    public Map<String, Seller> getSellers() {
        return sellers;
    }
}

