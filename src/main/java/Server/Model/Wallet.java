package Server.Model;

import Server.Model.Users.Person;

import java.util.ArrayList;

public class Wallet {
    private long money;
    private Person person;
    private static long minimumMoneyInWallet;
    private static ArrayList<Wallet> wallets = new ArrayList<>();

    public Wallet(long money, Person person) {
        this.money = money;
        this.person = person;
        wallets.add(this);
    }

    public static void setMinimumMoneyInWallet(long minimumMoneyInWallet) {
        Wallet.minimumMoneyInWallet = minimumMoneyInWallet;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getMoneyInAccess() {
        return money - minimumMoneyInWallet;
    }

    public void chargeWallet(long money) {
        this.money += money;
    }

    public void withdraw(long money) {
        this.money -= money;
    }

    public static ArrayList<Wallet> getWallets() {
        return wallets;
    }
}
