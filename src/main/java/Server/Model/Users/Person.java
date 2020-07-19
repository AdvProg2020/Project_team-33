package Server.Model.Users;

import Server.Database.SaveData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    protected String name, username, password, email, phone, family;
    public static ArrayList<Person> people = new ArrayList<>();
    SaveData saveData = new SaveData();

    @JsonCreator
    public Person(@JsonProperty("username") String username, @JsonProperty("name") String name, @JsonProperty("family") String family, @JsonProperty("phone") String phone,
                  @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.username = username;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.email = email;
        this.password = password;
        people.add(this);
    }

    public Person() {

    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setFamily(String newFamily) {
        this.family = newFamily;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public String getFamily() {
        return this.family;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }


    public static Person getPersonByUsername(String username) {
        for (Person person : people) {
            if (person.getUsername().equals(username)) {
                return person;
            }
        }
        return null;
    }

    public static boolean isAccountWithThisUsernameExist(String username) {
        for (Person eachPerson : people) {
            if (eachPerson.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void deleteUser(Person person) {
//        if (person instanceof Manager) {
//            Manager.deleteUser(person);
//        } else if (person instanceof Seller) {
//            Seller.deleteUser(person);
//        } else {
//            Buyer.deleteUser(person);
//        }
        people.remove(person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", family='" + family +
                '}';
    }
}