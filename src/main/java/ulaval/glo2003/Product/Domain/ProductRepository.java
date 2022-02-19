package ulaval.glo2003.Product.Domain;

import ulaval.glo2003.Product.Exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public Product find(String productId) {
        Product product = products.get(productId);
        if (product != null){
            return product;
        }
        else {
            throw new ProductNotFoundException();
        }
    }

    public void save(Product product) {
        String productId = product.getId();
        products.put(productId, product);
    }
}
