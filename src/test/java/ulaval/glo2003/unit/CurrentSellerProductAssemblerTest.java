package ulaval.glo2003.unit;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerProductAssembler;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class CurrentSellerProductAssemblerTest {

    private static CurrentSellerProductAssembler currentSellerProductAssembler;

    @BeforeAll
    public static void setup() {currentSellerProductAssembler = new CurrentSellerProductAssembler();}

    @Test
    void givenProductWithOffer_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();
        Offer offer = getOffer();

        product.addOffer(offer);

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(product.getCategories());
        assertThat(currentSellerProductAssemblerResponse.offers).isInstanceOf(DetailedProductOffersResponse.class);
    }

    @Test
    void givenProductNoOffer_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(product.getCategories());
        assertThat(currentSellerProductAssemblerResponse.offers).isInstanceOf(DetailedProductOffersResponse.class);
    }

    public Product getProduct() {
        String title = "Mister Clean";
        String description = "Wow, so good!";
        Double suggestedPrice = 34d;
        List<ProductCategory> categories = new ArrayList<>();
        ObjectId sellerId = new ObjectId();
        String sellerName = "John Doe";
        ObjectId id = new ObjectId();
        List<Offer> offers = new ArrayList<>();

        return new Product(title, description, suggestedPrice, categories, sellerId, sellerName, id, offers, OffsetDateTime.now(Clock.systemUTC()));
    }

    public Offer getOffer() {
        Double amount = 25.0;
        String message = "Significant message";
        ObjectId id = new ObjectId();
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "5989782222";

        return new Offer(id, amount, message, name ,email, phoneNumber);
    }
}
