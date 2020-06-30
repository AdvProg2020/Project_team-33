package Model.Users;

import Database.SaveData;
import Model.BuyLog;
import Model.Cart;

import java.util.ArrayList;

public class Buyer extends Person {
    private long money;
    private ArrayList<BuyLog> logs = new ArrayList<>();
    public static ArrayList<Buyer> allBuyers = new ArrayList<>();
    private ArrayList<String> discountCode = new ArrayList<>();

    public Buyer(String username, String name, String family, String phone,
                 String email, String password) {
        super(username, name, family, phone, email, password);
        allBuyers.add(this);
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
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

    public static ArrayList<Buyer> getAllBuyers() {
        return allBuyers;
    }
}