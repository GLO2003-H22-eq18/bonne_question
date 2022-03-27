package ulaval.glo2003.seller.domain;

import java.util.HashMap;
import java.util.Map;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.exceptions.SellerNotFoundException;

public interface SellerRepository {

    public abstract Seller findById(String sellerId);

    public abstract void save(Seller seller);

    public abstract Map<String, Seller> getSellers();

    public abstract void updateSeller(Product myProduct);
}
