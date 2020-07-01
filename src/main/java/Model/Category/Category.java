package Model.Category;

import Model.Product;

import java.util.ArrayList;

public class Category {
    private String name;
    private Category superCategory;
    private static ArrayList<Category> allCategory = new ArrayList<>();
    private ArrayList<Product> allProduct = new ArrayList<>();
    private ArrayList<String> details;

    public Category(String name, Category superCategory, ArrayList<String> details) {
        this.name = name;
        this.superCategory = this;
        this.details = details;
        allCategory.add(this);
    }

    public String getName() {
        return name;
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategory) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public static ArrayList<Category> getAllCategory() {
        return allCategory;
    }

    public static void deleteCategory(Category category) {
        allCategory.remove(category);
    }

    public String getDetail1() {
        return this.details.get(0);
    }

    public String getDetail2() {
        return this.details.get(1);
    }

    public String getDetail3() {
        return this.details.get(2);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail1(String detail) {
        this.details.set(0, detail);
    }

    public void setDetail2(String detail) {
        this.details.set(1, detail);
    }

    public void setDetail3(String detail) {
        this.details.set(2, detail);
    }

    public static boolean isCategoryExist(String name) {
        for (Category category : allCategory) {
            if (category.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addToProducts(Product product) {
        this.allProduct.add(product);
    }

    public static void changeProductCategory(Category oldCategory, Category newCategory, Product product) {
        newCategory.addToProducts(product);
        oldCategory.allProduct.remove(product);
    }

    public void addProduct(Product product) {
        this.allProduct.add(product);
    }
}
