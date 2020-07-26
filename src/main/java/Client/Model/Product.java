package Client.Model;

import Client.Model.Category.SubCategory;
import javafx.scene.image.ImageView;

import java.time.LocalTime;
import java.util.ArrayList;

public class Product {
    private String productID;
    private String name;
    private String condition;
    private String company;
    private long money;
    private String seller;
    private String category;
    private String inventoryStatus;
    private SubCategory subCategory;
    private String description;
    private double averageScore;
    private int numberOfProducts;
    private String requestCondition;
    private ImageView imageView;
    private LocalTime localTime;
    private ArrayList<Score> allScores = new ArrayList<>();
    private ArrayList<Comment> allComments = new ArrayList<>();

    public Product(String productID, String name, String company, long money, String seller,
                   String category, String description, int numberOfProducts) {
        this.productID = productID;
        this.name = name;
        this.company = company;
        this.money = money;
        this.seller = seller;
        this.category = category;
        this.description = description;
        this.numberOfProducts = numberOfProducts;
        this.localTime = LocalTime.now();
        this.localTime = LocalTime.now();
    }

    public ArrayList<Score> getAllScores() {
        return allScores;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public void addScore(Score score) {
        allScores.add(score);
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

    public String getSeller() {
        return seller;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
//
//    public static Product getProductById(String id){
//        for (Product product : allProducts) {
//            if (product.getProductID().equals(id)){
//                return product;
//            }
//        }
//        return null;
//    }
//
//    public void setInventoryStatus(String inventoryStatus) {
//        this.inventoryStatus = inventoryStatus;
//    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
//        if (this.getNumberOfProducts() == 0) {
//            allProducts.remove(this);
//        }
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

//    public static boolean isProductExist(String code) {
//        for (Product allProduct : allProducts) {
//            if (allProduct.getProductID().equals(code)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView getImageView() {
        return imageView;
    }
//
//    public static void deleteProduct(Product product) {
//        allProducts.remove(product);
//    }
//
//    public static ArrayList<Product> getAllProducts() {
//        return allProducts;
//    }
//
//    public boolean isBuyerBoughtThisProduct(Buyer buyer) {
//        return allBuyers.contains(buyer);
//    }
//
//    public void addComment(Comment comment) {
//        allComments.add(comment);
//    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public double getScore() {
        if (this.allScores.size() == 0) {
            return 0;
        }
        double score = 0;
        for (Score allScore : this.allScores) {
            score += allScore.getScore();
        }
        return (score / this.allScores.size());
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }
//
//    public void setImageView(String image) {
//        if (image.equals("digital")) {
//            this.imageView.setImage(digital);
//
//        } else if (image.equals("art")) {
//            this.imageView.setImage(art);
//
//        } else if (image.equals("book")) {
//            this.imageView.setImage(book);
//
//        } else if (image.equals("food")) {
//            this.imageView.setImage(food);
//
//        }
//    }

}
