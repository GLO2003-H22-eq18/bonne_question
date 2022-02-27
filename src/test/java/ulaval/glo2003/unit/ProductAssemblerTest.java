package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.Product.Domain.ProductCategory.toCategoriesList;
import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductCategory;
import ulaval.glo2003.Product.UI.ProductAssembler;
import ulaval.glo2003.Product.UI.ProductResponse;

class ProductAssemblerTest {

    private static ProductAssembler productAssembler;

    @BeforeAll
    public static void setUp() {
        productAssembler = new ProductAssembler();
    }

    public Product getProduct() {
        String title = "Clean Stuff";
        String description = "The cleanest of all the clean stuff.";
        Double suggestedPrice = 32d;
        String sellerId = "0";
        String sellerName = "John Doe";

        List<String> categoriesString = new ArrayList<>();
        categoriesString.add("beauty");
        categoriesString.add("sports");
        List<ProductCategory> categories = toCategoriesList(categoriesString);

        return new Product(title, description, suggestedPrice, categories, sellerId, sellerName);
    }

    @Test
    void givenProduct_whenCreateProductResponse_thenCorrectProductResponse() {
        Product product = getProduct();

        ProductResponse productResponse = productAssembler.createProductResponse(product);

        assertThat(productResponse.id).isEqualTo(product.getId());
        assertThat(productResponse.createdAt).isEqualTo(product.getCreatedAt());
        assertThat(productResponse.title).isEqualTo(product.getTitle());
        assertThat(productResponse.description).isEqualTo(product.getDescription());
        assertThat(productResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productResponse.categories).isEqualTo(toStringList(product.getCategories()));
        assertThat(productResponse.seller.id).isEqualTo(product.getSellerId());
        assertThat(productResponse.seller.name).isEqualTo(product.getSellerName());
        assertThat(productResponse.offers.mean).isEqualTo(product.getMean());
        assertThat(productResponse.offers.count).isEqualTo(product.getCount());
    }
}
