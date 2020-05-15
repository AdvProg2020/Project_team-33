package Model;

import java.util.ArrayList;

public class Buyer extends Person {
    private Cart userCart;
    private ArrayList<BuyLog> logs = new ArrayList<BuyLog>();
    public static ArrayList<Buyer> allBuyers = new ArrayList<Buyer>();
    private ArrayList<String> discountCode = new ArrayList<String>();
    private ArrayList<Cart> allCarts = new ArrayList<Cart>();
    private long money;

    public Buyer(String name, String family, String username, String password, String phone, String email,
                 long money, Cart cart) {
        super(name, family, username, password, phone, email);
        this.money = money;
        allBuyers.add(this);
        this.userCart = cart;
    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public static boolean isBuyerWithThisNameExist(String name) {
        for (Buyer buyer : allBuyers) {
            if (buyer.getName().equals(name)) return true;
        }
        return false;
    }

    public static Buyer getBuyerName(String name) {
        for (Buyer buyer : allBuyers) {
            if (buyer.getName().equals(name)) return buyer;
        }
        return null;
    }

    public void addDiscountCode(String code) {
        for (String codes : discountCode)
            if (codes.equals(code)) return;
        this.discountCode.add(code);
    }

    public void addLog(BuyLog log) {
        this.logs.add(log);
    }

    public ArrayList<BuyLog> getLog() {
        return this.logs;
    }

    public ArrayList<String> getDiscountCode() {
        return this.discountCode;
    }

    public long getMoney() {
        return money;
    }
}