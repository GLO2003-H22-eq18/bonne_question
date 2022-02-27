package ulaval.glo2003.Product.Domain;

import static ulaval.glo2003.Product.Domain.ProductCategory.toCategoriesList;

import java.util.ArrayList;
import java.util.List;
import ulaval.glo2003.Product.Exceptions.*;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Utils.StringUtil;

public class ProductFactory {
    public Product create(Seller productSeller, ProductRequest productRequest) {
        checkMissingParam(productRequest);
        checkInvalidParam(productRequest);

        List<ProductCategory> categories = createProductCategoryList(productRequest.categories);

        Product product =
                new Product(
                        productRequest.title,
                        productRequest.description,
                        productRequest.suggestedPrice,
                        categories,
                        productSeller.getId(),
                        productSeller.getName());

        return product;
    }

    private void checkMissingParam(ProductRequest productRequest) {
        if (productRequest.title == null) throw new MissingProductTitleException();
        else if (productRequest.description == null) throw new MissingProductDescriptionException();
        else if (productRequest.suggestedPrice == null)
            throw new MissingProductSuggestedPriceException();
    }

    private void checkInvalidParam(ProductRequest productRequest) {
        validateTitle(productRequest.title);
        validateDescription(productRequest.description);
        validateSuggestedPrice(productRequest.suggestedPrice);
        validateCategories(productRequest.categories);
    }

    private void validateTitle(String title) {
        if (StringUtil.removeEmptyChar(title).isEmpty()) {
            throw new InvalidProductTitleException();
        }
    }

    private void validateDescription(String description) {
        if (StringUtil.removeEmptyChar(description).isEmpty()) {
            throw new InvalidProductDescriptionException();
        }
    }

    private void validateCategories(List<String> names) {
        if (names != null && !names.isEmpty()) {
            for (String name : names) {
                if (!ProductCategory.contains(name)) {
                    throw new InvalidProductCategoriesException();
                }
            }
        }
    }

    private void validateSuggestedPrice(Double suggestedPrice) {
        if (suggestedPrice < 1.00d) {
            throw new InvalidProductSuggestedPriceException();
        }
    }

    private List<ProductCategory> createProductCategoryList(List<String> categories) {
        if (categories != null) {
            return toCategoriesList(categories);
        } else {
            return new ArrayList<>();
        }
    }
}
