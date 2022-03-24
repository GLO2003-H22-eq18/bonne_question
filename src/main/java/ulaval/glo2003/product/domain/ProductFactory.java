package ulaval.glo2003.product.domain;

import java.util.List;
import ulaval.glo2003.product.exceptions.InvalidProductCategoriesException;
import ulaval.glo2003.product.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.exceptions.InvalidProductSuggestedPriceException;
import ulaval.glo2003.product.exceptions.InvalidProductTitleException;
import ulaval.glo2003.product.exceptions.InvalidSellerIdException;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.utils.StringUtil;

public class ProductFactory {
    private static int currentId = 0;

    public Product create(Seller productSeller, ProductRequest productRequest) {
        checkNewProductInvalidParam(productRequest);

        return new Product(
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                ProductCategory.toCategoriesList(productRequest.categories),
                productSeller.getId(),
                productSeller.getName(),
                currentId++);
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
