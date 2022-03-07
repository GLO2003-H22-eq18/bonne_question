package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.ProductCategory;

public class ProductCategoryTest {

    private static final List<String> STRINGS_LIST = List.of("sports", "beauty", "other");
    private static final List<ProductCategory> CATEGORIES_LIST =
            List.of(ProductCategory.SPORTS, ProductCategory.BEAUTY, ProductCategory.OTHER);
    private static final String VALID_STRING_CATEGORY = "sports";
    private static final String INVALID_STRING_CATEGORY = "not a valid category";

    @Test
    void givenProductCategory_whenChangeToString_thenReturnCorrectString() {
        ProductCategory productCategory = ProductCategory.SPORTS;

        String productString = productCategory.toString();

        assertThat(productString).isEqualTo("sports");
    }

    @Test
    void givenValidStringCategory_whenCheckingIfEnumContainsString_thenReturnTrue() {
        Boolean containsString = ProductCategory.contains(VALID_STRING_CATEGORY);

        assertThat(containsString).isTrue();
    }

    @Test
    void givenInvalidStringCategory_whenCheckingIfEnumContainsString_thenReturnFalse() {
        Boolean containsString = ProductCategory.contains(INVALID_STRING_CATEGORY);

        assertThat(containsString).isFalse();
    }

    @Test
    void givenCategoryList_whenConvertToListOfString_thenCorrectList() {
        List<String> stringCategoryList = ProductCategory.toStringList(CATEGORIES_LIST);

        assertThat(stringCategoryList).containsExactlyElementsIn(STRINGS_LIST);
    }

    @Test
    void givenStringList_whenConvertToListOfCategory_thenCorrectList() {
        List<ProductCategory> categoryList = ProductCategory.toCategoriesList(STRINGS_LIST);

        assertThat(categoryList).containsExactlyElementsIn(CATEGORIES_LIST);
    }
}
