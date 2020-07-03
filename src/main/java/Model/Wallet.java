package Model;

import Model.Users.Person;

public class Wallet {
    private long money;
    private Person person;

    public Wallet(long money, Person person) {
        this.money = money;
        this.person = person;
    }
}
