package Client.Model.Category;

import Client.Model.Product;

import java.util.ArrayList;

public class Category {
    private String name;
    private Category superCategory;
    private ArrayList<Product> allProduct = new ArrayList<>();
    private ArrayList<String> details;

    public Category(String name, ArrayList<String> details) {
        this.name = name;
        this.superCategory = this;
        this.details = details;
    }

    public String getName() {
        return name;
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
}
