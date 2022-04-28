package ulaval.glo2003.product.domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.exceptions.InvalidProductCategoriesException;
import ulaval.glo2003.product.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.exceptions.InvalidProductSuggestedPriceException;
import ulaval.glo2003.product.exceptions.InvalidProductTitleException;
import ulaval.glo2003.product.exceptions.InvalidSellerIdException;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.utils.StringUtil;

public class ProductFactory {

    public Product create(Seller productSeller, ProductRequest productRequest) {
        checkNewProductInvalidParam(productRequest);

        return new Product(
                productRequest.title,
                productRequest.description,
                (Math.round(productRequest.suggestedPrice * 100.0) / 100.0),
                ProductCategory.toCategoriesList(productRequest.categories),
                productSeller.getId(),
                productSeller.getName(),
                new ObjectId(),
                new ArrayList<>(),
                new ArrayList<>(),
                OffsetDateTime.now(Clock.systemUTC()));
    }

    public static void checkNewProductInvalidParam(ProductRequest productRequest) {
        validateString(productRequest.title, "title");
        validateDescription(productRequest.description);
        validateSuggestedPrice(productRequest.suggestedPrice);
        validateCategories(productRequest.categories);
    }

    private static void validateString(String stringToValidate, String stringType) {
        if (StringUtil.removeEmptyChar(stringToValidate).isEmpty()) {
            if (stringType.equals("title")) {
                throw new InvalidProductTitleException();
            } else if (stringType.equals("sellerId")) {
                throw new InvalidSellerIdException();
            }
        }
    }

    private static void validateDescription(String description) {
        if (StringUtil.removeEmptyChar(description).isEmpty()) {
            throw new InvalidProductDescriptionException();
        }
    }

    private static void validateCategories(List<String> names) {
        if (names != null && !names.isEmpty()) {
            for (String name : names) {
                if (!ProductCategory.contains(name)) {
                    throw new InvalidProductCategoriesException();
                }
            }
        }
    }

    private static void validateSuggestedPrice(Double suggestedPrice) {
        if (suggestedPrice < 1.00d) {
            throw new InvalidProductSuggestedPriceException();
        }
    }
}
