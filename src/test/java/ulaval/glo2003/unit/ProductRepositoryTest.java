package ulaval.glo2003.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.product.domain.ProductCategory.toCategoriesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ulaval.glo2003.ApplicationContext;
import ulaval.glo2003.product.infrastructure.repository.MongoProductsRepository;

import ulaval.glo2003.product.domain.Offer;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.exceptions.InvalidPriceTypeException;
import ulaval.glo2003.product.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.infrastructure.assemblers.ProductModelAssembler;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;
import ulaval.glo2003.product.infrastructure.assemblers.OfferModelAssembler;
import ulaval.glo2003.seller.infrastructure.assemblers.SellerModelAssembler;

public class ProductRepositoryTest {

    private static int currentProductId = 0;
    private static final String INVALID_ID = "-1";
    private static final String INVALID_TITLE = "Noone has this title muahaha!";
    private static final List<String> INVALID_CATEGORIES = Arrays.asList("NOT A CATEGORY", "XD");
    private static final String TITLE_1 = "The rock";
    private static final String DESCRIPTION_1 = "The Rock's rock";
    private static final double SUGGESTED_PRICE_1 = 1000;
    private static final List<ProductCategory> CATEGORIES_1 =
            Arrays.asList(ProductCategory.BEAUTY, ProductCategory.OTHER);
    private static final String SELLER_ID_1 = "1";
    private static final String SELLER_NAME_1 = "Dwayne Johnson";
    private static final String TITLE_2 = "The cape of invisibility";
    private static final String DESCRIPTION_2 = "Deathly hallow";
    private static final double SUGGESTED_PRICE_2 = 1000000;
    private static final List<ProductCategory> CATEGORIES_2 =
            Arrays.asList(ProductCategory.APPAREL, ProductCategory.OTHER);
    private static final String SELLER_ID_2 = "2";
    private static final String SELLER_NAME_2 = "Harry Potter";
    private static final String TITLE_1_AND_2_IN_COMMON = "The";
    private static final List<ProductCategory> CATEGORIES_ONLY_IN_1 =
            Arrays.asList(ProductCategory.BEAUTY);
    private static final double MIDDLE_PRICE = 10000;
    private ProductRepository productRepository;

    private final ApplicationContext applicationContext = new ApplicationContext();
    private final OfferModelAssembler offerModelAssembler = new OfferModelAssembler();
    private final ProductModelAssembler productModelAssembler = new ProductModelAssembler(offerModelAssembler);
    private final SellerModelAssembler sellerModelAssembler = new SellerModelAssembler(productModelAssembler);

    private static final List<Offer> OFFERS = new ArrayList();

    public ProductRepositoryTest() {
    }


    @BeforeEach
    void setUp() {
        productRepository = new MongoProductsRepository(applicationContext, offerModelAssembler, productModelAssembler);
    }

    @Test
    void givenProductId_whenProductExists_thenFindCorrectProduct() {
        Product product =
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS);
        productRepository.save(product);

        Product gottenProduct = productRepository.findById(product.getId());

        assertThat(gottenProduct).isEqualTo(product);
    }

    @Test
    void givenProductId_whenProductDoesntExists_thenThrowProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> productRepository.findById(INVALID_ID));
    }

    @Test
    void givenSellerId_whenProductsWithSellerIdInRepo_thenReturnSellerIdsProducts() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));
        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(SELLER_ID_1, null, new ArrayList<>(), null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsHaveSameSellerId(products, SELLER_ID_1)).isTrue();
    }

    @Test
    void givenSellerId_whenNoProductsWithSellerIdInRepo_thenReturnNoProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(INVALID_ID, null, new ArrayList<>(), null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(products).isEmpty();
    }

    @Test
    void givenTitle_whenProductsWithTitleInRepo_thenReturnCorrectProducts() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, TITLE_1, new ArrayList<>(), null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsHaveTitle(products, TITLE_1)).isTrue();
    }

    @Test
    void givenPartOfTitle_whenProductsWithPartOfTitleInRepo_thenReturnCorrectProducts() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, TITLE_1_AND_2_IN_COMMON, new ArrayList<>(), null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsHaveTitle(products, TITLE_1_AND_2_IN_COMMON)).isTrue();
    }

    @Test
    void givenTitle_whenNoProductsWithTitleInRepo_thenReturnNoProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, INVALID_TITLE, new ArrayList<>(), null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);


        assertThat(products).isEmpty();
    }

    @Test
    void givenOneCategory_whenProductsWithCategoryInRepo_thenReturnCorrectProducts() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        List<String> categories = ProductCategory.toStringList(CATEGORIES_ONLY_IN_1);

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, categories, null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsHaveAtLeastOneGoodCategory(products, CATEGORIES_ONLY_IN_1))
                .isTrue();
    }

    @Test
    void givenMultipleCategories_whenProductsWithCategoryInRepo_thenReturnCorrectProducts() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        List<String> categories = ProductCategory.toStringList(CATEGORIES_2);

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, categories, null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsHaveAtLeastOneGoodCategory(products, CATEGORIES_2)).isTrue();
    }

    @Test
    void givenCategories_whenNoProductsWithCategoryInRepo_thenReturnNoProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, INVALID_CATEGORIES, null, null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(products).isEmpty();
    }

    @Test
    void givenMinPrice_whenProductsWithCorrectPriceInRepo_thenReturnCorrectProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, new ArrayList<>(), Double.toString(MIDDLE_PRICE), null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsPriceIsGreaterThanOrEqualToMinPrice(products, MIDDLE_PRICE))
                .isTrue();
    }

    @Test
    void givenMinPrice_whenAProductWithPriceEqualToMinPriceInRepo_thenReturnCorrectProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, new ArrayList<>(), Double.toString(SUGGESTED_PRICE_1), null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsPriceIsGreaterThanOrEqualToMinPrice(products, SUGGESTED_PRICE_1))
                .isTrue();
    }

    @Test
    void givenMaxPrice_whenProductsWithCorrectPriceInRepo_thenReturnCorrectProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, new ArrayList<>(), null, Double.toString(MIDDLE_PRICE));

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsPriceIsGreaterThanOrEqualToMaxPrice(products, MIDDLE_PRICE))
                .isTrue();
    }

    @Test
    void givenMinPrice_whenAProductWithPriceEqualToMaxPriceInRepo_thenReturnCorrectProduct() {
        productRepository.save(
                new Product(
                        TITLE_1,
                        DESCRIPTION_1,
                        SUGGESTED_PRICE_1,
                        CATEGORIES_1,
                        SELLER_ID_1,
                        SELLER_NAME_1,
                        String.valueOf(currentProductId++),
                        OFFERS));

        productRepository.save(
                new Product(
                        TITLE_2,
                        DESCRIPTION_2,
                        SUGGESTED_PRICE_2,
                        CATEGORIES_2,
                        SELLER_ID_2,
                        SELLER_NAME_2,
                        String.valueOf(currentProductId++),
                        OFFERS));

        FilteredProductRequest filteredProductRequest = createFilteredProductRequest(null, null, new ArrayList<>(), Double.toString(SUGGESTED_PRICE_1), null);

        List<Product> products =
                productRepository.getFilteredProducts(filteredProductRequest);

        assertThat(verifyProductsPriceIsGreaterThanOrEqualToMinPrice(products, SUGGESTED_PRICE_1))
                .isTrue();
    }

    private static FilteredProductRequest createFilteredProductRequest(String sellerId, String title, List<String> categories, String minPrice, String maxPrice) {
        FilteredProductRequest filteredProductRequest = new FilteredProductRequest();
        filteredProductRequest.sellerId = sellerId;
        filteredProductRequest.title = title;
        filteredProductRequest.categories = createProductCategoryList(categories);
        if (minPrice != null) {
            filteredProductRequest.minPrice = parsePriceToDouble(minPrice);
        }
        if (maxPrice != null) {
            filteredProductRequest.maxPrice = parsePriceToDouble(maxPrice);
        }

        return filteredProductRequest;
    }

    private static Double parsePriceToDouble(String price) {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new InvalidPriceTypeException();
        }
    }

    private static List<ProductCategory> createProductCategoryList(List<String> categories) {
        if (categories != null) {
            return toCategoriesList(categories);
        } else {
            return new ArrayList<>();
        }
    }

    private static boolean verifyProductsHaveSameSellerId(List<Product> products, String sellerId) {
        boolean areAllValid = true;
        for (Product product : products) {
            if (!product.getSellerId().equals(sellerId)) {
                areAllValid = false;
                break;
            }
        }
        return areAllValid;
    }

    private static boolean verifyProductsHaveTitle(List<Product> products, String title) {
        boolean areAllValid = true;
        String lowerCaseTitle = title.toLowerCase();
        for (Product product : products) {
            System.out.println(product.getTitle());
            if (!product.getTitle().toLowerCase().contains(lowerCaseTitle)) {
                areAllValid = false;
                break;
            }
        }
        return areAllValid;
    }

    private static boolean verifyProductsHaveAtLeastOneGoodCategory(
            List<Product> products, List<ProductCategory> productCategories) {
        boolean areAllValid = true;
        for (Product product : products) {
            boolean isCategoryPresent = false;
            for (ProductCategory category : productCategories) {
                if (product.getCategories().contains(category)) {
                    isCategoryPresent = true;
                    break;
                }
            }
            if (!isCategoryPresent) {
                areAllValid = false;
                break;
            }
        }
        return areAllValid;
    }
  
    private static boolean verifyProductsPriceIsGreaterThanOrEqualToMinPrice(

            List<Product> products, double minPrice) {
        boolean areAllValid = true;
        for (Product product : products) {
            if (product.getSuggestedPrice() < minPrice) {
                areAllValid = false;
                break;
            }
        }
        return areAllValid;
    }

    private static boolean verifyProductsPriceIsGreaterThanOrEqualToMaxPrice(
            List<Product> products, double maxPrice) {
        boolean areAllValid = true;
        for (Product product : products) {
            if (product.getSuggestedPrice() > maxPrice) {
                areAllValid = false;
                break;
            }
        }
        return areAllValid;
    }
}
