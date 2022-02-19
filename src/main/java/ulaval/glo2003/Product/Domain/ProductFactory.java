package ulaval.glo2003.Product.Domain;

import ulaval.glo2003.Product.Exceptions.*;
import ulaval.glo2003.Product.UI.ProductRequest;
import ulaval.glo2003.Seller.Domain.Seller;
import ulaval.glo2003.Seller.Domain.SellerRepository;

import java.util.List;

public class ProductFactory {
    SellerRepository sellerRepository;

    public ProductFactory(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Product create(ProductRequest productRequest, String sellerId) {

        checkMissingParam(productRequest);
        checkInvalidParam(productRequest);

        Seller productSeller = sellerRepository.find(sellerId);

        return new Product(
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                productRequest.categories,
                productSeller.getId(),
                productSeller.getName());
    }

    private Seller getSeller(String sellerId){
        // TODO: connect with SellerRepository by using find(sellerId) method
        return null;
    }

    private void checkMissingParam(ProductRequest productRequest){
         if(productRequest.title == null)
            throw new MissingProductTitleException();
         else if(productRequest.description == null)
            throw new MissingProductDescriptionException();
         else if(productRequest.suggestedPrice == null)
            throw new MissingProductSuggestedPriceException();
    }

    private void checkInvalidParam(ProductRequest productRequest){
        validateTitle(productRequest.title);
        validateDescription(productRequest.description);
        validateSuggestedPrice(productRequest.suggestedPrice);
        validateCategories(productRequest.categories);
    }

    private void validateTitle(String title) {
        if(removeEmptyChar(title).isEmpty()){
            throw new InvalidProductTitleException();
        }
    }

    private void validateDescription(String description) {
        if(removeEmptyChar(description).isEmpty()){
            throw new InvalidProductDescriptionException();
        }
    }

    private static void validateCategories(List<String> categories) {
        if (categories != null && !categories.isEmpty()){
            categories.forEach((category) -> {
                if(removeEmptyChar(category).isEmpty()){
                    throw new InvalidProductCategoriesException();
                }});
        }
    }

    private static void validateSuggestedPrice(Double suggestedPrice) {
        if(suggestedPrice < 1.00d){
            throw new InvalidProductSuggestedPriceException();
        }
    }

    private static String removeEmptyChar(String string){
        return string
                .replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll(" ", "")
                .replaceAll("0", "");
    }
}

