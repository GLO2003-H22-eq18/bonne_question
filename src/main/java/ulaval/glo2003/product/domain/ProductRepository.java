package ulaval.glo2003.product.domain;

import java.util.List;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;

public interface ProductRepository {

    public void save(Product product);

    public Product findById(ObjectId productId);

    public List<Product> getFilteredProducts(
            FilteredProductRequest filteredProductRequest);

    public List<Product> getProductsFilterBySellerId(ObjectId sellerId);

    public List<Product> getProductsFilterByTitle(
            List<Product> filteredProductsList, String title);

    public List<Product> getProductsFilterByCategories(
            List<Product> filteredProductsList, List<ProductCategory> categories);

    public List<Product> getMinPriceFilteredProducts(
            List<Product> filteredProductsList, Double minPrice);

    public List<Product> getMaxPriceFilteredProducts(
            List<Product> filteredProductsList, Double maxPrice);

    public abstract void updateOffer(Offer myOffer, ObjectId productId);

    public abstract void updateView(View myView, ObjectId productId);
}
