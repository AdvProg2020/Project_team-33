package Client.Model.Category;

import Client.Model.Product;

import java.util.ArrayList;

public class SubCategory extends Category {
    private ArrayList<Product> products;

    public SubCategory(String name, Category superCategory, ArrayList<String> details) {
        super(name, details);
        this.products = new ArrayList<>();
    }
}
