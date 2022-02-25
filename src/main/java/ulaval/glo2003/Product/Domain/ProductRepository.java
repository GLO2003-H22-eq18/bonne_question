package ulaval.glo2003.Product.Domain;

import ulaval.glo2003.Product.Exceptions.ProductNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public void save(Product product) {
        String productId = product.getId();
        products.put(productId, product);
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

    public List<Product> getFilteredProducts(String sellerId, String title, List<String> categories, Double minPrice, Double maxPrice) {
        List<Product> filteredProductsList;
        if (sellerId != null) {
            filteredProductsList = getSellerIdFilteredProducts(sellerId);
        } else {
            filteredProductsList = new ArrayList<>(products.values());
        }

        if (title != null) {
            filteredProductsList = getTitleFilteredProducts(filteredProductsList, title);
        }

        if (!categories.isEmpty()) {
            filteredProductsList = getCategoriesFilteredProducts(filteredProductsList, categories);
        }

        if (minPrice != null) {
            filteredProductsList = getMinPriceFilteredProducts(filteredProductsList, minPrice);
        }

        if (maxPrice != null) {
            filteredProductsList = getMaxPriceFilteredProducts(filteredProductsList, maxPrice);
        }

        return filteredProductsList;
    }

    private List<Product> getSellerIdFilteredProducts (String sellerId) {
        return products.values()
                .stream()
                .filter(product -> product.getSellerId().equals(sellerId))
                .collect(Collectors.toList());
    }

    private List<Product> getTitleFilteredProducts (List<Product> filteredProductsList, String title) {
        final String lowerCaseTitle = title.toLowerCase(Locale.ROOT);
         return filteredProductsList
                .stream()
                .filter(product -> product.getTitle().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    private List<Product> getCategoriesFilteredProducts (List<Product> filteredProductsList, List<String> categories) {
        List<ProductCategory> productCategories = ProductCategory.toCategoriesList(categories);
        return filteredProductsList
                .stream()
                .filter(product -> !Collections.disjoint(product.getCategories(), productCategories))
                .collect(Collectors.toList());
    }

    private List<Product> getMinPriceFilteredProducts (List<Product> filteredProductsList, Double minPrice) {
        return filteredProductsList
                .stream()
                .filter(product -> product.getSuggestedPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    private List<Product> getMaxPriceFilteredProducts (List<Product> filteredProductsList, Double maxPrice) {
        return filteredProductsList
                .stream()
                .filter(product -> product.getSuggestedPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
