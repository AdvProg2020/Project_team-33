package Server.Model;

import Server.Model.Users.Person;

public class Wallet {
    private long money;
    private Person person;
    private double minimumMoneyInWallet;

    public Wallet(long money, Person person) {
        this.money = money;
        this.person = person;
    }

    public double getMinimumMoneyInWallet() {
        return minimumMoneyInWallet;
    }

    public void setMinimumMoneyInWallet(double minimumMoneyInWallet) {
        this.minimumMoneyInWallet = minimumMoneyInWallet;
    }

    public void chargeWallet(long money){
        this.money += money;
    }

    public void withdraw(long money){
        this.money -= money;
    }
}
