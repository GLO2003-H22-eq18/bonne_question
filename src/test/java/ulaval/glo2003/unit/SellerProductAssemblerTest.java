package ulaval.glo2003.unit;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.Product.Domain.Product;
import ulaval.glo2003.Product.Domain.ProductCategory;
import ulaval.glo2003.Seller.UI.SellerProductAssembler;
import ulaval.glo2003.Seller.UI.SellerProductResponse;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class SellerProductAssemblerTest {

    private SellerProductAssembler sellerProductAssembler = new SellerProductAssembler();

    @Before
    public void setUp() {
        sellerProductAssembler = new SellerProductAssembler();
    }

    public Product getProduct() {
        String title = "Mister Clean";
        String description = "Wow, so good!";
        Double suggestedPrice = 34d;
        List<ProductCategory> categories = new ArrayList<>();
        String sellerId = "1";
        String sellerName = "John Doe";

        return new Product(title, description, suggestedPrice, categories, sellerId, sellerName);
    }

    @Test
    void givenSeller_whenCreateSellerResponse_thenCorrectSellerResponse() {
        Product product = getProduct();

        SellerProductResponse sellerProductResponse = sellerProductAssembler.createSellerProductResponse(product);

        assertThat(sellerProductResponse.id).isEqualTo(product.getId());
        assertThat(sellerProductResponse.createdAt).isEqualTo(product.getCreatedAt());
        assertThat(sellerProductResponse.title).isEqualTo(product.getTitle());
        assertThat(sellerProductResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(sellerProductResponse.description).isEqualTo(product.getDescription());
        assertThat(sellerProductResponse.categories).isEqualTo(product.getCategories());
        assertThat(sellerProductResponse.offers.count).isEqualTo(product.getCount());
        assertThat(sellerProductResponse.offers.mean).isEqualTo(product.getMean());
    }
}
