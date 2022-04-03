package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerAssembler;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static ulaval.glo2003.product.domain.ProductCategory.toCategoriesList;

public class CurrentSellerAssemblerTest {

    private static final ObjectId ID = new ObjectId();
    private static final String NAME = "John";
    private static final OffsetDateTime CREATED_AT = OffsetDateTime.now(Clock.systemUTC());
    private static final String BIO = "Sick bio!";
    private static final LocalDate BIRTHDATE = LocalDate.parse("1977-04-23");
    private static final List<Product> PRODUCTS = new ArrayList<>();

    private static CurrentSellerAssembler currentSellerAssembler;

    @BeforeAll
    public static void setup(){
        currentSellerAssembler = new CurrentSellerAssembler();
    }

    @Test
    void givenSellerWithProduct_whenCreateCurrentSellerResponse_thenCorrectSellerResponse(){
        Seller seller = getSeller();
        Product product = getProduct();
        Offer offer = getOffer();

        seller.addProduct(product);
        product.addOffer(offer);

        CurrentSellerResponse currentSellerResponse = currentSellerAssembler.createCurrentSellerResponse(seller);

        assertThat(currentSellerResponse.name).isEqualTo(seller.getName());
        assertThat(currentSellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(currentSellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(currentSellerResponse.birthDate).isEqualTo(seller.getBirthDate().toString());
        assertThat(currentSellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(currentSellerResponse.products.size()).isEqualTo(seller.getProducts().size());
    }

    @Test
    void givenSellerNoProduct_whenCreateCurrentSellerResponse_thenCorrectSellerResponse(){
        Seller seller = getSeller();

        CurrentSellerResponse currentSellerResponse = currentSellerAssembler.createCurrentSellerResponse(seller);

        assertThat(currentSellerResponse.name).isEqualTo(seller.getName());
        assertThat(currentSellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(currentSellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(currentSellerResponse.birthDate).isEqualTo(seller.getBirthDate().toString());
        assertThat(currentSellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(currentSellerResponse.products.size()).isEqualTo(seller.getProducts().size());
    }

    public Seller getSeller(){
        return new Seller(ID, NAME, BIO, CREATED_AT, BIRTHDATE, PRODUCTS);
    }

    public Product getProduct() {
        String title = "Clean Stuff";
        String description = "The cleanest of all the clean stuff.";
        Double suggestedPrice = 32d;
        String sellerName = "John Doe";
        List<Offer> offers = new ArrayList();

        List<String> categoriesString = new ArrayList<>();
        categoriesString.add("beauty");
        categoriesString.add("sports");
        List<ProductCategory> categories = toCategoriesList(categoriesString);

        return new Product(title, description, suggestedPrice, categories, new ObjectId(), sellerName, new ObjectId(), offers,
                OffsetDateTime.now(Clock.systemUTC()));
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
