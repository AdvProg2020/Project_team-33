package Model.Category;

import Model.Product;

import java.util.ArrayList;

public class SubCategory extends Category {
    private ArrayList<Product> products;

    public SubCategory(String name, Category superCategory, ArrayList<String> details) {
        super(name, superCategory, details);
        this.products = new ArrayList<>();
    }
}
