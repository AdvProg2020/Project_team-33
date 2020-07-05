package Model.Users;

import Model.Logs.BuyLog;
import Model.Cart;
import Model.Discount;
import Model.Product;
import Model.Wallet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

public class Buyer extends Person {
    private long money;
    private Wallet wallet;
    private double minimumMoneyInWallet;
    private boolean isOnline;
    private ImageView imageView;
    private final Image unknownPerson = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
    private final Image womanPerson = new Image(Paths.get("src/main/java/view/images/womanLogo.png").toUri().toString());
    private final Image manPerson = new Image(Paths.get("src/main/java/view/images/manLogo.png").toUri().toString());
    private ArrayList<BuyLog> logs = new ArrayList<>();
    private HashMap<BuyLog, ArrayList<Product>> buyLogProducts = new HashMap<>();
    private Cart cart;
    public static ArrayList<Buyer> allBuyers = new ArrayList<>();
    private ArrayList<Discount> discountCode = new ArrayList<>();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
        this.imageView = new ImageView(unknownPerson);
        this.cart = new Cart();
        this.wallet = new Wallet(0 ,this);
        allBuyers.add(this);
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

    public void setMinimumMoneyInWallet(double minimumMoneyInWallet) {
        this.minimumMoneyInWallet = minimumMoneyInWallet;
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
        return this.discountCode;
    }

    public static ArrayList<Buyer> getAllBuyers() {
        return allBuyers;
    }

    public void setImageView(String sex) {
        if (sex.equals("man")) {
            this.imageView.setImage(manPerson);
        } else if (sex.equals("woman")) {
            this.imageView.setImage(womanPerson);
        } else {
            this.imageView.setImage(unknownPerson);
        }
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

    public void addDiscount(Discount discount) {
        this.discountCode.add(discount);
    }
}