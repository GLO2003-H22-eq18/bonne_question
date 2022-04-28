package ulaval.glo2003.seller.domain;

import java.util.Map;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Product;

public interface SellerRepository {

    public abstract Seller findById(ObjectId sellerId);

    public abstract void save(Seller seller);

    public abstract Map<ObjectId, Seller> getSellers();

    public abstract void updateSeller(Product myProduct);
}
