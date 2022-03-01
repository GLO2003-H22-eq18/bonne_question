package ulaval.glo2003.Seller.UI;

import ulaval.glo2003.Product.Domain.Product;

import java.util.List;

import static ulaval.glo2003.Product.Domain.ProductCategory.toStringList;

public class SellerProductAssembler {

    public static SellerProductResponse createSellerProductResponse(Product product) {
        System.out.println("Created sellerproductresponse: " + product.getId());
        String id = product.getId();
        String createdAt = product.getCreatedAt().toString();
        String title = product.getTitle();
        String description = product.getDescription();
        Double suggestedPrice = product.getSuggestedPrice();
        List<String> categories = toStringList(product.getCategories());
        Integer count = product.getCount();
        //TODO: Double mean = product.getMean(); --> AJOUTER POUR TP3

        return new SellerProductResponse(id, createdAt, title, description, suggestedPrice, categories,
                count);
    }
}
