package ulaval.glo2003.Seller.Domain;

import ulaval.glo2003.Seller.Exceptions.SellerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class SellerRepository {
    private final Map<String, Seller> sellers;

    public SellerRepository() {
        sellers = new HashMap<>();
    }

    public Seller find(String sellerId) {
        Seller seller = sellers.get(sellerId);
        if (seller != null){
            return seller;
        }
        else {
            throw new SellerNotFoundException();
        }
    }

    public void save(Seller seller) {
        String sellerId = seller.getId();
        sellers.put(sellerId, seller);
    }

    public Map<String, Seller> getSellers() {
        return sellers;
    }
}
