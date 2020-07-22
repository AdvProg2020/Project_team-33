package Client.Model;

import Client.Model.Users.Person;

public class Wallet {
    private long money;
    private Person person;
    private static long minimumMoneyInWallet;

    public Wallet(long money, Person person) {
        this.money = money;
        this.person = person;
    }

    public double getMinimumMoneyInWallet() {
        return minimumMoneyInWallet;
    }

    public void setMinimumMoneyInWallet(long minimumMoneyInWallet) {
        Wallet.minimumMoneyInWallet = minimumMoneyInWallet;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getMoneyInAccess(){
        return money - minimumMoneyInWallet;
    }

    public void chargeWallet(long money){
        this.money += money;
    }

    public void withdraw(long money){
        this.money -= money;
    }
}
