package Model;

import Model.Category.Category;
import Model.Category.SubCategory;
import Model.Requests.RequestAddProduct;
import Model.Users.Buyer;
import Model.Users.Seller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    private String productID;
    private String name;
    private String condition;
    private String company;
    private long money;
    private Seller seller;
    private String inventoryStatus;
    private Category category;
    private SubCategory subCategory;
    private String description;
    private double averageScore;
    private int numberOfProducts = 0;
    private String requestCondition;
    private ArrayList<Score> allScores = new ArrayList<>();
    private ArrayList<Comment> allComments = new ArrayList<>();
    private ArrayList<SellerOfProduct> allSeller = new ArrayList<>();
    private static ArrayList<Product> allProducts = new ArrayList<>();

    public Product(String productID, String name, String company, long money, Seller seller,
                   Category category, String description, String requestCondition) {
        this.productID = productID;
        this.name = name;
        this.company = company;
        this.money = money;
        this.seller = seller;
        this.category = category;
        this.description = description;
        this.numberOfProducts++;
        this.requestCondition = requestCondition;
        new RequestAddProduct("Add product", "Unknown", seller, this);
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public String getCompany() {
        return company;
    }

    public long getMoney() {
        return money;
    }

    public Seller getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public static boolean isProductExist(String code) {
        for (Product allProduct : allProducts) {
            if (allProduct.getProductID().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCondition(String condition) {
        this.condition = condition;
        if (condition.equals("Accept")) {
            allProducts.add(this);
            this.getSeller().addProduct(this);
        }
    }

    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }
}
