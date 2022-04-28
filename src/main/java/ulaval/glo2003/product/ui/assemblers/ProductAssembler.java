package ulaval.glo2003.product.ui.assemblers;

import static ulaval.glo2003.product.domain.ProductCategory.toCategoriesList;
import static ulaval.glo2003.product.domain.ProductCategory.toStringList;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductCategory;
import ulaval.glo2003.product.exceptions.InvalidPriceTypeException;
import ulaval.glo2003.product.exceptions.MissingProductDescriptionException;
import ulaval.glo2003.product.exceptions.MissingProductSuggestedPriceException;
import ulaval.glo2003.product.exceptions.MissingProductTitleException;
import ulaval.glo2003.product.ui.requests.FilteredProductRequest;
import ulaval.glo2003.product.ui.requests.ProductRequest;
import ulaval.glo2003.product.ui.responses.ProductOffersResponse;
import ulaval.glo2003.product.ui.responses.ProductResponse;
import ulaval.glo2003.product.ui.responses.ProductSellerResponse;

public class ProductAssembler {

    private final ProductSellerAssembler productSellerAssembler;
    private final ProductOffersAssembler productOffersAssembler;

    public ProductAssembler() {
        productSellerAssembler = new ProductSellerAssembler();
        productOffersAssembler = new ProductOffersAssembler();
    }

    public ProductResponse createProductResponse(Product product) {
        String id = product.getId().toString();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        ProductSellerResponse seller =
                productSellerAssembler.createProductSellerResponse(product.getSellerId(),
                        product.getSellerName());
        ProductOffersResponse offers =
                productOffersAssembler.createProductOffersResponse(product.getOffers());

        return new ProductResponse(id, createdAt, title, description, suggestedPrice, categories,
                seller, offers);
    }

    public FilteredProductRequest createFilteredProductRequest(String sellerId, String title,
                                                               List<String> categories,
                                                               String minPrice, String maxPrice) {
        FilteredProductRequest filteredProductRequest = new FilteredProductRequest();

        if (sellerId != null) {
            if (sellerId.length() == 24) {
                filteredProductRequest.sellerId = new ObjectId(sellerId);
            } else {
                filteredProductRequest.sellerId = new ObjectId(); // create ObjectId that no one has
            }
        }

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


    public void checkProductRequestMissingParams(ProductRequest productRequest) {
        checkMissingProductTitle(productRequest.title);
        checkMissingProductDescription(productRequest.description);
        checkMissingProductPrice(productRequest.suggestedPrice);
    }

    private void checkMissingProductTitle(String title) {
        if (title == null) {
            throw new MissingProductTitleException();
        }
    }

    private void checkMissingProductDescription(String description) {
        if (description == null) {
            throw new MissingProductDescriptionException();
        }
    }

    private void checkMissingProductPrice(Double price) {
        if (price == null) {
            throw new MissingProductSuggestedPriceException();
        }
    }

    private Double parsePriceToDouble(String price) {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new InvalidPriceTypeException();
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
