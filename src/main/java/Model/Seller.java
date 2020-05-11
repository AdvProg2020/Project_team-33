package Model;

import java.util.ArrayList;

public class Seller extends  Person{
    public static ArrayList<Seller> allSellers = new ArrayList<Seller>();
    private ArrayList<SellLog> logs = new ArrayList<SellLog>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private String description ;
    public Seller(String name , String family , String username , String password , String phone , String email , String description)
    {
        super(name , family , username , password , phone , email);
        allSellers.add(this);
        this.description = description ;
    }

    public String getDescription() {
        return this.description;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void deleteProduct(Product product){
        for (Product eachProduct : products)
            if (eachProduct.equals(product)) this.products.remove(product);
    }

    public boolean isThisSellerHasThisProduct(Product product){
        for(Product eachProduct : products)
            if (eachProduct.equals(product)) return true;
        return  false;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<SellLog> getLogs() {
        return this.logs;
    }

    public void addSellLog(SellLog log){
        this.logs.add(log);
    }

    public static boolean isSellerWithThisNameExist(String name){
        for (Seller eachSeller : allSellers)
            if (eachSeller.getName().equals(name)) return true ;
        return  false ;
    }
}
