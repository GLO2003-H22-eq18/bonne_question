package ulaval.glo2003.Product.Domain;

public enum ProductCategory {
    SPORTS ("sports"),
    ELECTRONICS ("electronics"),
    APPAREL ("apparel"),
    BEAUTY ("beauty"),
    HOUSING ("housing"),
    OTHER ("other");

    private final String name;

    ProductCategory(String category) {
        name = category;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
