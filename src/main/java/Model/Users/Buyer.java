package Model.Users;

import Database.SaveData;
import Model.BuyLog;
import Model.Cart;

import java.util.ArrayList;

public class Buyer extends Person {
    private Cart userCart;
    private ArrayList<BuyLog> logs = new ArrayList<>();
    public static ArrayList<Buyer> allBuyers = new ArrayList<>();
    private ArrayList<String> discountCode = new ArrayList<>();
    private long money;
    SaveData saveData = new SaveData();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
        allBuyers.add(this);
    }

    public static void deleteBuyer(Person person) {
        allBuyers.remove((Buyer) person);
    }

    public Cart getUserCart() {
        return userCart;
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