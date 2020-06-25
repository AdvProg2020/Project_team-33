package Model;

import java.util.ArrayList;

public class Category {
    private String name;
    public static ArrayList<Category> allCategory = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public Category(String name, Category superCategory) {
        this.name = name;
        allCategory.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public boolean isThisCategoryHasThisProduct(Product product) {
        for (Product eachProduct : this.products) {
            if (eachProduct.equals(product)) {
                return true;
            }
        }
        return false;
    }
}
