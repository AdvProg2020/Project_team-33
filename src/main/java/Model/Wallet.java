package Model;

import Model.Users.Person;

public class Wallet {
    private long money;
    private Person person;

    public Wallet(long money, Person person) {
        this.money = money;
        this.person = person;
    }

    public void chargeWallet(long money){
        this.money += money;
    }

    public void withdraw(long money){
        this.money -= money;
    }
}
