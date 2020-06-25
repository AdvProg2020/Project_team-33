package Model.Logs;

import Model.Product;
import Model.Users.Buyer;

import java.util.ArrayList;
import java.util.Date;

public class SellLog {
    public static ArrayList<SellLog> allSellLogs = new ArrayList<SellLog>();
    private double moneyThatPaid ;
    private Buyer buyer;
    private int sellLogId;
    private double discount ;
    private ArrayList<Product> products = new ArrayList<Product>();
    private boolean isThisProductSent ;
    private Date timeOfSelling;

    public SellLog(Buyer buyer , double moneyThatPaid , double discount , Date timeOfSelling , boolean isThisProductSent){
        this.buyer = buyer ;
        this.moneyThatPaid = moneyThatPaid;
        this.discount = discount ;
        this.timeOfSelling = timeOfSelling ;
        this.isThisProductSent = isThisProductSent ;
        sellLogId = allSellLogs.size();
        allSellLogs.add(this);
    }

    public void setProducts(ArrayList<Product> productsThatSold){
        this.products.addAll(productsThatSold);
    }

    public int getSellLogId() {
        return sellLogId;
    }

    public double getMoneyThatPaid() {
        return this.moneyThatPaid;
    }

    public void setMoneyThatPaid(double moneyThatPaid) {
        this.moneyThatPaid = moneyThatPaid;
    }

    public Buyer getBuyer() {
        return this.buyer;
    }

    public double getDiscount() {
        return this.discount;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }
}
