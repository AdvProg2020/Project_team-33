package Model;

import java.util.ArrayList;

public class Product {
    public static ArrayList<Product> allProduct = new ArrayList<Product>();
    private ArrayList<Seller> allSeller = new ArrayList<Seller>();
    private String description , name;
    private int productID ;
    private ArrayList<Score> allScore = new ArrayList<Score>();
    private ArrayList<Comment> allComments = new ArrayList<Comment>();
    private ArrayList<Buyer> buyers = new ArrayList<Buyer>();
    private double price;
    public static int numberOfProductsFromBegin;
    private productState state;
    private Category productCategory;
    private boolean isProductAvailable;
    public Product(String name , String description , double price , String state , Category category , boolean isProductAvailable)
    {
        allProduct.add(this);
        this.name = name ;
        this.description = description ;
        this.price = price ;
        this.state = productState.valueOf(state);
        this.productCategory = category ;
        this.isProductAvailable = isProductAvailable;
        numberOfProductsFromBegin++;
        this.productID = numberOfProductsFromBegin ;
    }
    public void changeCategory(Category newCategory){
        this.productCategory = newCategory ;
    }
    public void addSeller(Seller seller){
        this.allSeller.add(seller);
    }
    public void removeSeller(Seller seller){
        this.allSeller.remove(seller);
    }
    public void changeDescription(String newDescription){
        this.description = newDescription;
    }
    public double getPrice(){
        return this.price;
    }
    public void changePrice(double newPrice){
        this.price = newPrice;
    }
    public boolean isProductWithThisNameExist(String name){
        for (Product eachProduct : allProduct)
            if (eachProduct.getName().equals(name)) return true ;
        return true ;

    }
    public Product getProductWithName(String name) {
        for (Product eachProduct : allProduct)
            if (eachProduct.getName().equals(name)) return eachProduct;
        return null;
    }
    public Product getProductWithID(int ID) {
        for (Product eachProduct : allProduct)
            if (eachProduct.getID() == ID) return eachProduct;
        return null;
    }
    public int getID(){
        return this.productID;
    }
    public String getName(){
        return  this.name ;
    }
    public String getDescription(){
        return  this.description ;
    }
    public Category getProductCategory(){
        return  this.productCategory;
    }
    public String getProductState()
    {
        switch (this.state){
            case BUILDING:
                return "Product is Building";
            case EDITING:
                return "Product is Editing";
            case CONFIRMED:
                return "Product is Ready for Buy";
        }
        return  null;
    }
    public boolean getIsProductAvailable(){
        return  this.isProductAvailable;
    }
    public ArrayList<Seller> getAllSeller(){
        return this.allSeller;
    }
    public ArrayList<Comment> getAllComments(){
        return this.allComments;
    }
    public ArrayList<Score> getAllScore(){
        return this.allScore;
    }
    public void addComment(Comment comment){
        this.allComments.add(comment);
    }
    public void addScore(Score score){
        this.allScore.add(score);
    }
    public void addBuyer(Buyer buyer){
        this.buyers.add(buyer);
    }

    public ArrayList<Buyer> getBuyers() {
        return this.buyers;
    }
}
enum productState
{
    BUILDING,
    EDITING,
    CONFIRMED
}
