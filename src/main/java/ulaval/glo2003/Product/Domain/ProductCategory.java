package ulaval.glo2003.Product.Domain;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ProductCategory {
    SPORTS("sports"),
    ELECTRONICS("electronics"),
    APPAREL("apparel"),
    BEAUTY("beauty"),
    HOUSING("housing"),
    OTHER("other");

    private final String name;
    private static final List<ProductCategory> VALUES = Arrays.asList(ProductCategory.values());
    private static final Random RANDOM = new Random();
    private static final int SIZE = VALUES.size();

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

    public static List<String> getRandomCategories(){
        List<ProductCategory> randomCategories = new ArrayList<>();
        for (int i=0; i < RANDOM.nextInt(6); i++){
            randomCategories.add(VALUES.get(RANDOM.nextInt(SIZE)));
        }
        return toStringList(randomCategories);
    }

    public static List<ProductCategory> toCategoriesList(List<String> names) {
        List<ProductCategory> categories = new ArrayList<>();
        names.forEach((name) -> categories.add(findByName(name)));
        return categories;
    }

    public static List<String> toStringList(List<ProductCategory> categories) {
        List<String> names = new ArrayList<>();
        categories.forEach((category) -> names.add(category.toString()));
        return names;
    }
}
