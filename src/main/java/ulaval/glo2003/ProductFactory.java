package ulaval.glo2003;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {
    public static Product create(ProductRequest productRequest, String sellerId) {

        //checkMissingParam(productRequest);
        //checkInvalidParam(productRequest);

        // TODO: replace with Seller productSeller = SellerRepository.find(sellerId);
        Seller productSeller = new Seller("John Doe", "This is my bio", new ArrayList<String>());

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
        // TODO: create products missing exceptions (and uncomment this block)
        /**
         if(productRequest.title == null)
         throw new MissingProductTitleException();
         else if(productRequest.description == null)
         throw new MissingProductDescriptionException();
         else if(productRequest.suggestedPrice == null)
         throw new MissingProductSuggestedPriceException();
         else if(productRequest.categories == null)
         throw new MissingProductCategoriesException();
         */

    }

    private void checkInvalidParam(ProductRequest productRequest){
        validateTitle(productRequest.title);
        validateDescription(productRequest.description);
        validateSuggestedPrice(productRequest.suggestedPrice);
        validateCategories(productRequest.categories);
    }

    private void validateTitle(String title) {
        if(removeEmptyChar(title).isEmpty()){
            //throw new InvalidProductTitleException();
        }
    }

    private void validateDescription(String description) {
        if(removeEmptyChar(description).isEmpty()){
            //throw new InvalidProductDescriptionException();
        }
    }

    private static void validateCategories(List<String> categories) {
        // TODO: validate categories
    }

    private static void validateSuggestedPrice(Double suggestedPrice) {
        if(suggestedPrice < 1.00d){
            //throw new InvalidProductSuggestedPriceException();
        }
    }

    private String removeEmptyChar(String string){
        return string
                .replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll(" ", "")
                .replaceAll("0", "");
    }
}

