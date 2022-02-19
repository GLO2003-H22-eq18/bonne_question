package ulaval.glo2003.Product.Domain;

import ulaval.glo2003.Product.Exceptions.ProductNotFoundException;
import ulaval.glo2003.Product.UI.ProductResponse;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Product> getFilteredProducts(String sellerId, String title, List<String> categories, Double minPrice, Double maxPrice) {
//        TODO CHANGE CATEGORIES TYPE WHEN IT IS CREATED
        List<Product> filteredProductsList = new ArrayList<>(products.values());

        if (sellerId != null) {
            filteredProductsList = products.values()
                    .stream()
                    .filter(product -> product.getSellerId().equals(sellerId))
                    .collect(Collectors.toList());
        }

        if (title != null) {
            final String lowerCaseTitle = title.toLowerCase(Locale.ROOT);
            filteredProductsList = filteredProductsList
                    .stream()
                    .filter(product -> product.getTitle().contains(lowerCaseTitle))
                    .collect(Collectors.toList());
        }

        if (!categories.isEmpty()) {
            filteredProductsList = filteredProductsList
                    .stream()
                    .filter(product -> !Collections.disjoint(product.getCategories(), categories))
                    .collect(Collectors.toList());
        }

        if (minPrice != null) {
            filteredProductsList = filteredProductsList
                    .stream()
                    .filter(product -> product.getSuggestedPrice() >= minPrice)
                    .collect(Collectors.toList());
        }

        if (maxPrice != null) {
            filteredProductsList = filteredProductsList
                    .stream()
                    .filter(product -> product.getSuggestedPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        return filteredProductsList;
    }
}
