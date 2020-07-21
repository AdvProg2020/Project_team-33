package Client.Model;

import Client.Model.Category.Category;
import Client.Model.Category.SubCategory;
import Client.Model.Users.Buyer;
import Client.Model.Users.Seller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.time.LocalTime;
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
    final private Image image = new Image(Paths.get("src/main/java/view/images/product.png").toUri().toString());
    final private Image digital = new Image(Paths.get("src/main/java/view/images/digital.png").toUri().toString());
    final private Image art = new Image(Paths.get("src/main/java/view/images/art.png").toUri().toString());
    final private Image book = new Image(Paths.get("src/main/java/view/images/book.png").toUri().toString());
    final private Image food = new Image(Paths.get("src/main/java/view/images/food.png").toUri().toString());

    private ImageView imageView;
    private LocalTime localTime;
    private ArrayList<Score> allScores = new ArrayList<>();
    private ArrayList<Comment> allComments = new ArrayList<>();
    private ArrayList<Buyer> allBuyers = new ArrayList<>();
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
        this.imageView = new ImageView(image);
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

    public Seller getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public static Product getProductById(String id){
        for (Product product : allProducts) {
            if (product.getProductID().equals(id)){
                return product;
            }
        }
        return null;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

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
            this.getCategory().addToProducts(this);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public boolean isBuyerBoughtThisProduct(Buyer buyer) {
        return allBuyers.contains(buyer);
    }

    public void addComment(Comment comment) {
        allComments.add(comment);
    }

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

    public void setImageView(String image) {
        if (image.equals("digital")) {
            this.imageView.setImage(digital);

        } else if (image.equals("art")) {
            this.imageView.setImage(art);

        } else if (image.equals("book")) {
            this.imageView.setImage(book);

        } else if (image.equals("food")) {
            this.imageView.setImage(food);

        }
    }

}
