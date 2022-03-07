package ulaval.glo2003.Product.UI;

import static ulaval.glo2003.Product.Domain.ProductCategory.toCategoriesList;

import java.util.ArrayList;
import java.util.List;
import ulaval.glo2003.Product.Domain.ProductCategory;
import ulaval.glo2003.Product.Exceptions.InvalidPriceTypeException;
import ulaval.glo2003.Product.Exceptions.InvalidProductCategoriesException;
import ulaval.glo2003.Product.Exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.Product.Exceptions.InvalidProductSuggestedPriceException;
import ulaval.glo2003.Product.Exceptions.InvalidProductTitleException;
import ulaval.glo2003.Product.Exceptions.InvalidSellerIdException;
import ulaval.glo2003.Product.Exceptions.MissingProductDescriptionException;
import ulaval.glo2003.Product.Exceptions.MissingProductSuggestedPriceException;
import ulaval.glo2003.Product.Exceptions.MissingProductTitleException;
import ulaval.glo2003.Utils.StringUtil;

public class ProductUtil {
    public static void checkMissingParam(ProductRequest productRequest) {
        if (productRequest.title == null) {
            throw new MissingProductTitleException();
        } else if (productRequest.description == null) {
            throw new MissingProductDescriptionException();
        } else if (productRequest.suggestedPrice == null) {
            throw new MissingProductSuggestedPriceException();
        }
    }

    public static void checkNewProductInvalidParam(ProductRequest productRequest) {
        validateString(productRequest.title, "title");
        validateDescription(productRequest.description);
        validateSuggestedPrice(productRequest.suggestedPrice);
        validateCategories(productRequest.categories);
    }

    public static void checkFilteredProductInvalidParam(String sellerId, String title,
                                                        List<String> categories, String minPrice,
                                                        String maxPrice) {
        if (sellerId != null) {
            validateString(sellerId, "sellerId");
        }
        if (title != null) {
            validateString(title, "title");
        }
        if (!categories.isEmpty()) {
            validateCategories(categories);
        }
        if (minPrice != null) {
            validatePriceType(minPrice);
        }
        if (maxPrice != null) {
            validatePriceType(maxPrice);
        }
    }

    public static List<ProductCategory> createProductCategoryList(List<String> categories) {
        if (categories != null) {
            return toCategoriesList(categories);
        } else {
            return new ArrayList<>();
        }
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

    private static void validatePriceType(String price) {
        try {
            Double num = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new InvalidPriceTypeException();
        }
    }
}
