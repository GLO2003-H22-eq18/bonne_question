package ulaval.glo2003.Product.Domain;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

public enum ProductCategory {
    SPORTS("sports"),
    ELECTRONICS("electronics"),
    APPAREL("apparel"),
    BEAUTY("beauty"),
    HOUSING("housing"),
    OTHER("other");

    private final String name;

    ProductCategory(String category) {
        name = category;
    }

    private boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static boolean contains(String category) {
        for (ProductCategory productCategory : values()) {
            if (productCategory.equalsName(category)) {
                return true;
            }
        }
        return false;
    }

    private static ProductCategory findByName(String name) {
        for (ProductCategory category : values()) {
            if (category.equalsName(name)) {
                return category;
            }
        }
        return null;
    }

    public static List<ProductCategory> toCategoriesList(List<String> names) {
        List<ProductCategory> categories = new ArrayList<>();
        names.forEach((name) -> categories.add(findByName(name)));
        return new ArrayList<>(
                new HashSet<>(categories));
    }

    public static List<String> toStringList(List<ProductCategory> categories) {
        List<String> names = new ArrayList<>();
        categories.forEach((category) -> names.add(category.toString()));

        return new ArrayList<>(
                new HashSet<>(names));
    }
}
