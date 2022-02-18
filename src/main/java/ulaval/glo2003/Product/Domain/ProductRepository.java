package ulaval.glo2003.Product.Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public Optional<Product> find(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public void save(Product product) {
        String productId = product.getId();
        products.put(productId, product);
    }
}
