package ulaval.glo2003.Product.Domain;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

public class Product {
  private final String id;
  private final OffsetDateTime createdAt;
  private final String title;
  private final String description;
  private final Double suggestedPrice;
  private final List<ProductCategory> categories;
  private final String sellerId;
  private final String sellerName;
  private Double mean;
  private Integer count;

  private static int currentId = 0;

  public Product(
      String title,
      String description,
      Double suggestedPrice,
      List<ProductCategory> categories,
      String sellerId,
      String sellerName) {
    this.id = String.valueOf(currentId++);
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.categories = categories;
    this.sellerId = sellerId;
    this.sellerName = sellerName;
    this.count = 0; // for TP2 only

    this.createdAt = OffsetDateTime.now(Clock.systemUTC());
  }

  public String getId() {
    return id;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Double getSuggestedPrice() {
    return suggestedPrice;
  }

  public List<ProductCategory> getCategories() {
    return categories;
  }

  public String getSellerId() {
    return sellerId;
  }

  public String getSellerName() {
    return sellerName;
  }

  public Double getMean() {
    return null;
    //        TODO: livrable3
    //        return mean;
  }

  public Integer getCount() {
    return count;
  }
}