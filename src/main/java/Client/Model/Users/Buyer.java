package Client.Model.Users;

import Client.Model.Cart;
import Client.Model.Discount;
import Client.Model.Logs.BuyLog;
import Client.Model.Product;
import Client.Model.Wallet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Person implements Serializable {
    private long money;
    private Wallet wallet;
    private double minimumMoneyInWallet;
    private boolean isOnline;
    private ArrayList<BuyLog> logs = new ArrayList<>();
    private HashMap<BuyLog, ArrayList<Product>> buyLogProducts = new HashMap<>();
    private Cart cart;
    private ArrayList<Discount> discountCode = new ArrayList<>();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
        this.cart = new Cart();
        this.wallet = new Wallet(0, this);
    }

    public Buyer() {

    }

//    public Wallet getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(Wallet wallet) {
//        this.wallet = wallet;
//    }
//
//    public double getMinimumMoneyInWallet() {
//        return minimumMoneyInWallet;
//    }
//
//    public void setMinimumMoneyInWallet(double minimumMoneyInWallet) {
//        this.minimumMoneyInWallet = minimumMoneyInWallet;
//    }
//
//    public boolean isOnline() {
//        return isOnline;
//    }
//
//    public void setOnline(boolean online) {
//        isOnline = online;
//    }
//
//    public long getMoney() {
//        return money;
//    }
//
//    public void setMoney(long money) {
//        this.money = money;
//    }
//
//    public void addLog(BuyLog log) {
//        this.logs.add(log);
//        this.buyLogProducts.put(log, log.getProducts());
//    }
//
//    public ArrayList<Product> getProductsInLog(BuyLog buyLog) {
//        return this.buyLogProducts.get(buyLog);
//    }
//
//    public ArrayList<BuyLog> getLog() {
//        return this.logs;
//    }
//
//    public ArrayList<Discount> getDiscountCode() {
//        return this.discountCode;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public boolean isDiscountExist(String code) {
//        for (Discount discount : this.discountCode) {
//            if (discount.getCode().equals(code)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void addDiscount(Discount discount) {
//        this.discountCode.add(discount);
//    }
}