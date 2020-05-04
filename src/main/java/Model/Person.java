package Model;

import java.util.ArrayList;

public class Person {
    protected String name, username, password, email, phone, family;
    protected int money;
    private static ArrayList<Person> people = new ArrayList<Person>();

    public Person(String name, String family, String username, String password, String phone, String email) {
        this.name = name;
        this.family = family;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        people.add(this);
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeFamily(String newFamily) {
        this.family = newFamily;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changePhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getName() {
        return this.name;
    }

    public String getFamily() {
        return this.family;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public static Person getPersonByUsername(String username) {
        for (Person person : people) {
            if (person.getUsername().equals(username)) {
                return person;
            }
        }
        return null;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void deleteUser(String username){
        people.remove(getPersonByUsername(username));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", family='" + family + '\'' +
                ", money=" + money +
                '}';
    }
}