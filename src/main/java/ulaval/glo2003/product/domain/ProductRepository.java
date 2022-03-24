package ulaval.glo2003.product.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;

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
            FilteredProductRequest filteredProductRequest) {

        List<Product> filteredProductsList;
        if (filteredProductRequest.sellerId != null) {
            filteredProductsList = getProductsFilterBySellerId(filteredProductRequest.sellerId);
        } else {
            filteredProductsList = new ArrayList<>(products.values());
        }

        if (filteredProductRequest.title != null) {
            filteredProductsList = getProductsFilterByTitle(filteredProductsList, filteredProductRequest.title);
        }

        if (!filteredProductRequest.categories.isEmpty()) {
            filteredProductsList = getProductsFilterByCategories(filteredProductsList, filteredProductRequest.categories);
        }

        if (filteredProductRequest.minPrice != null) {
            filteredProductsList =
                    getMinPriceFilteredProducts(filteredProductsList, filteredProductRequest.minPrice);
        }

        if (filteredProductRequest.maxPrice != null) {
            filteredProductsList =
                    getMaxPriceFilteredProducts(filteredProductsList, filteredProductRequest.maxPrice);
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
            List<Product> filteredProductsList, List<ProductCategory> categories) {
        return filteredProductsList.stream()
                .filter(
                        product ->
                                !Collections.disjoint(product.getCategories(), categories))
                .collect(Collectors.toList());
    }

    public List<Product> getMinPriceFilteredProducts(
            List<Product> filteredProductsList, Double minPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public List<Product> getMaxPriceFilteredProducts(
            List<Product> filteredProductsList, Double maxPrice) {
        return filteredProductsList.stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
