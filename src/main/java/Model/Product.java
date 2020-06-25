package Model;

import Model.Users.Buyer;
import Model.Users.Seller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> allProducts = new ArrayList<>();
    private ArrayList<SellerOfProduct> allSeller = new ArrayList<>();
    private String description, name;
    private int productID;
    private ArrayList<Score> allScores = new ArrayList<>();
    private ArrayList<Comment> allComments = new ArrayList<>();
    private ArrayList<Buyer> buyers = new ArrayList<>();
    public static int numberOfProductsFromBegin = 0;
    private productState state;
    private Category productCategory;
    private boolean isProductAvailable;
    private int seenNumber;
    private LocalDate buildingTime;

    public Product(String name, String description, double price, String state, Category category,
                   boolean isProductAvailable) {
        allProducts.add(this);
        this.name = name;
        this.description = description;
        this.state = productState.valueOf(state);
        this.productCategory = category;
        this.isProductAvailable = isProductAvailable;
        numberOfProductsFromBegin++;
        this.productID = numberOfProductsFromBegin;
        this.seenNumber = 0;
        this.buildingTime = LocalDate.now();
    }

    public int calculateAveragePrice(){
        int sum = 0;
        int number = 0;
        for (SellerOfProduct sellerOfProduct : allSeller) {
            sum += sellerOfProduct.getPrice();
            number++;
        }
        return sum / number;
    }

    public static void removeProduct(int Id) {
        allProducts.remove(getProductWithID(Id));
    }

    public int getProductID() {
        return productID;
    }

    public void changeCategory(Category newCategory) {
        this.productCategory = newCategory;
    }

    public void addSeller(Seller seller, double price) {
        this.allSeller.add(new SellerOfProduct(seller, price));
    }

    public void removeSeller(Seller seller) {
        this.allSeller.remove(seller);
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public static boolean isProductWithThisNameExist(String name) {
        for (Product eachProduct : allProducts)
            if (eachProduct.getName().equals(name)) return true;
        return true;
    }

    public static Product getProductWithName(String name) {
        for (Product eachProduct : allProducts)
            if (eachProduct.getName().equals(name)) return eachProduct;
        return null;
    }

    public static Product getProductWithID(int ID) {
        for (Product eachProduct : allProducts)
            if (eachProduct.getID() == ID) return eachProduct;
        return null;
    }

    public SellerOfProduct getSellerByName(String name) {
        for (SellerOfProduct seller : allSeller) {
            if (seller.getSeller().equals(name)) {
                return seller;
            }
        }
        return null;
    }

    public LocalDate getBuildingTime() {
        return this.buildingTime;
    }

    public int getID() {
        return this.productID;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getProductCategory() {
        return this.productCategory;
    }

    public String getProductState() {
        switch (this.state) {
            case BUILDING:
                return "Product is Building";
            case EDITING:
                return "Product is Editing";
            case CONFIRMED:
                return "Product is Ready for Buy";
        }
        return null;
    }

    public boolean getIsProductAvailable() {
        return this.isProductAvailable;
    }

    public boolean isBuyerBoughtThisProduct(Buyer buyer) {
        return this.getBuyers().contains(buyer);
    }

    public ArrayList<SellerOfProduct> getAllSeller() {
        return this.allSeller;
    }

    public ArrayList<Comment> getAllComments() {
        return this.allComments;
    }

    public ArrayList<Score> getAllScores() {
        return this.allScores;
    }

    public void addComment(Comment comment) {
        this.allComments.add(comment);
    }

    public void addScore(Score score) {
        this.allScores.add(score);
    }

    public void addBuyer(Buyer buyer) {
        this.buyers.add(buyer);
    }

    public ArrayList<Buyer> getBuyers() {
        return this.buyers;
    }

//    public boolean isProductExistInCategory(Category category) {
//        if (this.getProductCategory() == null) return false;
//        else if (this.getProductCategory().equals(category)) return true;
//        Category example = this.getProductCategory();
//        while (example.isCategoryHasSuper()) {
//            example = example.getSuperCategory();
//            if (example.equals(category)) return true;
//        }
//        return false;
//    }

    public double findPriceOfThisSeller(Seller seller) {
        for (SellerOfProduct example : allSeller)
            if (seller.equals(example.getSeller())) return example.getPrice();
        return 0;
    }

    public double findLeastPrice() {
        double leastPrice = allSeller.get(0).getPrice();
        for (SellerOfProduct sellerOfProduct : allSeller) {
            if (sellerOfProduct.getPrice() < leastPrice) {
                leastPrice = sellerOfProduct.getPrice();
            }
        }
        return leastPrice;
    }
}

enum productState {
    BUILDING,
    EDITING,
    CONFIRMED
}
