package ulaval.glo2003.unit.Assembler;

import static ulaval.glo2003.product.domain.ProductCategory.toCategoriesList;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.seller.domain.Seller;

public class AssemblerUnitTestUtils {

    public static Product getProduct() {
        String title = "Clean Stuff";
        String description = "The cleanest of all the clean stuff.";
        Double suggestedPrice = 20.0;
        String sellerName = "John Doe";
        List<Offer> offers = new ArrayList();
        List<View> views = new ArrayList();

        List<String> categoriesString = new ArrayList<>();
        categoriesString.add("beauty");
        categoriesString.add("sports");
        List<ProductCategory> categories = toCategoriesList(categoriesString);

        return new Product(title, description, suggestedPrice, categories, new ObjectId(),
                sellerName, new ObjectId(), offers, views,
                OffsetDateTime.now(Clock.systemUTC()));
    }

    public static Seller getSeller() {
        ObjectId id = new ObjectId();
        String name = "John";
        OffsetDateTime created_at = OffsetDateTime.now(Clock.systemUTC());
        String bio = "Sick bio!";
        LocalDate birthdate = LocalDate.parse("1977-04-23");
        List<Product> products = new ArrayList<>();

        return new Seller(id, name, bio, created_at, birthdate, products);
    }

    public static Offer getOffer(Double amount) {
        String message =
                "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
        ObjectId id = new ObjectId();
        OffsetDateTime createdAt = OffsetDateTime.now(Clock.systemUTC());
        String name = "John";
        String email = "sickmail@hotmail.com";
        String phoneNumber = "59897822222";

        return new Offer(id, createdAt, amount, message, name, email, phoneNumber);
    }

    public static View getView() {
        ObjectId id = new ObjectId();
        OffsetDateTime createdAt = OffsetDateTime.now(Clock.systemUTC());

        return new View(id, createdAt);
    }
}
