package Server.Model.Users;

import Server.Model.*;
import Server.Model.Logs.BuyLog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Person implements Serializable {
    private long money;
    private Wallet wallet;
    private static long minimumMoneyInWallet;
    private ImageView imageView;
    private transient ArrayList<BuyLog> logs = new ArrayList<>();
    private transient HashMap<BuyLog, ArrayList<Product>> buyLogProducts = new HashMap<>();
    private Supporter supporter;
    private ArrayList<Chat> chats;
    private Cart cart;
    private static double Wage;
    public static ArrayList<Buyer> allBuyers = new ArrayList<>();
    private ArrayList<Discount> discountCode = new ArrayList<>();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
//        this.imageView = new ImageView(unknownPerson);
        this.cart = new Cart();
        this.wallet = new Wallet(0, this);
        chats = new ArrayList<>();
        allBuyers.add(this);
    }

    public Buyer() {

    }

    public static void setWage(double parseDouble) {
    }

    public Supporter getSupporter() {
        return supporter;
    }

    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void clearChat() {
        chats.clear();
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public double getMinimumMoneyInWallet() {
        return minimumMoneyInWallet;
    }

    public static void setMinimumMoneyInWallet(long minimumMoneyInWallet) {
        Buyer.minimumMoneyInWallet = minimumMoneyInWallet;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void addLog(BuyLog log) {
        this.logs.add(log);
        this.buyLogProducts.put(log, log.getProducts());
    }

    public ArrayList<Product> getProductsInLog(BuyLog buyLog) {
        return this.buyLogProducts.get(buyLog);
    }

    public ArrayList<BuyLog> getLog() {
        return this.logs;
    }

    public ArrayList<Discount> getDiscountCode() {
        checkDiscountTime();
        return this.discountCode;
    }

    public static ArrayList<Buyer> getAllBuyers() {
        return allBuyers;
    }

    public void setImageView(String sex) {
//        if (sex.equals("man")) {
//            this.imageView.setImage(manPerson);
//        } else if (sex.equals("woman")) {
//            this.imageView.setImage(womanPerson);
//        } else {
//            this.imageView.setImage(unknownPerson);
//        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isDiscountExist(String code) {
        for (Discount discount : this.discountCode) {
            if (discount.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static Buyer getBuyerByUsername(String username) {
        for (Buyer allBuyer : allBuyers) {
            if (allBuyer.getUsername().equals(username)) {
                return allBuyer;
            }
        }
        return null;
    }

    public void addDiscount(Discount discount) {
        this.discountCode.add(discount);
    }

    public void checkDiscountTime() {
        for (Discount allDiscount : discountCode) {
            if (LocalTime.now().compareTo(allDiscount.getStartTime()) > 0) {
                if (LocalTime.now().compareTo(allDiscount.getEndTime()) > 0) {
                    discountCode.remove(allDiscount);
                }
            }

        }
    }
}