package ulaval.glo2003.product.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;

public interface ProductRepository {

    public void save(Product produc);

    public Product findById(String productId);

    public List<Product> getFilteredProducts(
            FilteredProductRequest filteredProductRequest);

    public List<Product> getProductsFilterBySellerId(String sellerId);

    private List<Product> getProductsFilterByTitle(
            List<Product> filteredProductsList, String title) {
        final String lowerCaseTitle = title.toLowerCase();
        return filteredProductsList.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<ProductCategory> categories);

    public List<Product> getMinPriceFilteredProducts(
            List<Product> filteredProductsList, Double minPrice);

    public List<Product> getMaxPriceFilteredProducts(
            List<Product> filteredProductsList, Double maxPrice);
}
