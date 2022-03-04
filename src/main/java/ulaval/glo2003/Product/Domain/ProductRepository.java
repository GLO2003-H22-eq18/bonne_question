package ulaval.glo2003.Product.Domain;

import java.util.*;
import java.util.stream.Collectors;
import ulaval.glo2003.Product.Exceptions.ProductNotFoundException;

public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public void save(Product product) {
        String productId = product.getId();
        products.put(productId, product);
    }

    public Product findById(String productId) {
        Product product = products.get(productId);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }

    public List<Product> getFilteredProducts(
            String sellerId,
            String title,
            List<String> categories,
            Double minPrice,
            Double maxPrice) {

        List<Product> filteredProductsList;
        if (sellerId != null) {
            filteredProductsList = getProductsFilterBySellerId(sellerId);
        } else {
            filteredProductsList = new ArrayList<>(products.values());
        }

        if (title != null) {
            filteredProductsList = getProductsFilterByTitle(filteredProductsList, title);
        }

        if (!categories.isEmpty()) {
            filteredProductsList = getProductsFilterByCategories(filteredProductsList, categories);
        }

        if (minPrice != null) {
            filteredProductsList = getProductsFilterByMinPrice(filteredProductsList, minPrice);
        }

        if (maxPrice != null) {

            filteredProductsList = getProductsFilterByMaxPrice(filteredProductsList, maxPrice);
        }

        return filteredProductsList;
    }

    public List<Product> getProductsFilterBySellerId(String sellerId) {
        return products.values().stream()
                .filter(product -> product.getSellerId().equals(sellerId))
                .collect(Collectors.toList());
    }

    private List<Product> getProductsFilterByTitle(
            List<Product> filteredProductsList, String title) {
        final String lowerCaseTitle = title.toLowerCase();
        return filteredProductsList.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<String> categories) {
        List<ProductCategory> productCategories = ProductCategory.toCategoriesList(categories);
        return filteredProductsList.stream()
                .filter(
                        product ->
                                !Collections.disjoint(product.getCategories(), productCategories))
                .collect(Collectors.toList());
    }

/*
    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<String> categories) {
        List<ProductCategory> productCategories = ProductCategory.toCategoriesList(categories);
        return filteredProductsList.stream()
                .filter(
                        product ->
                                product.getCategories().stream()
                                        .anyMatch(new HashSet<>(productCategories)::contains))
                .collect(Collectors.toList());
    }
*/

    public List<Product> getProductsFilterByMinPrice(
            List<Product> filteredProductsList, Double minPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFilterByMaxPrice(
            List<Product> filteredProductsList, Double maxPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
