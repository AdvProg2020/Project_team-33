package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog {
    public static ArrayList<BuyLog> allBuyLogs = new ArrayList<BuyLog>();
    private int logId ;
    private double moneyThatPaid , discount ;
    private String buyerName ;
    private Date timeOfBuyingProducts ;
    private HashMap<Product , Integer> products = new HashMap <Product , Integer>();
    private boolean isProductRecieved ;
    public BuyLog(String buyerName , Date date , double moneyThatPaid , double discount , boolean isProductRecieved)
    {
        this.isProductRecieved = isProductRecieved ;
        this.timeOfBuyingProducts = date ;
        this.moneyThatPaid = moneyThatPaid ;
        this.discount = discount ;
        this.buyerName = buyerName ;
        allBuyLogs.add(this);
    }
    public void addProduct(Product product , Integer num)
    {
        this.products.put(product , num);
    }
    public double getMoneyThatPaid()
    {
        return this.moneyThatPaid ;
    }
    public double getDiscount()
    {
        return this.discount ;
    }
    public Date getTimeOfBuyingProducts()
    {
        return this.timeOfBuyingProducts;
    }
    public HashMap<Product , Integer> getProducts()
    {
        return this.products;
    }
    public boolean getIsProductsRecieved()
    {
        return this.isProductRecieved;
    }
    public void setIsProductRecieved()
    {
        this.isProductRecieved = true ;
    }
}
