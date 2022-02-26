package ulaval.glo2003.Seller.UI;

import java.time.OffsetDateTime;
import java.util.List;
import ulaval.glo2003.Product.UI.ProductOffersResponse;

public class SellerProductResponse {
  public String id;
  public OffsetDateTime createdAt;
  public String title;
  public String description;
  public Double suggestedPrice;
  public ProductOffersResponse offers;
  public List<String> categories;

  public SellerProductResponse(
      String id,
      OffsetDateTime createdAt,
      String title,
      String description,
      Double suggestedPrice,
      Double mean,
      Integer count,
      List<String> categories) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = new ProductOffersResponse(count, mean);
    this.categories = categories;
  }
}
