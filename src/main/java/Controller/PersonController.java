package Controller;

import Model.Buyer;
import Model.Manager;
import Model.Person;
import Model.Seller;
import View.Menu;

import java.util.regex.Matcher;

public class PersonController {
    public static Person currentPerson;
    private Person person;
    private Seller seller;
    private Buyer buyer;
    private Manager manager;

    public static void editPersonalInfo(Person person,String field,String newChange) {
        if (field.equalsIgnoreCase("password")) {
            person.changePassword(newChange);
        } else if (field.equalsIgnoreCase("name")) {
            person.changeName(newChange);
        } else if (field.equalsIgnoreCase("family")) {
            person.changeFamily(newChange);
        } else if (field.equalsIgnoreCase("phone")) {
            person.changePhone(newChange);
        } else if (field.equalsIgnoreCase("email")) {
            person.changeEmail(newChange);
        }
    }

    public static void deleteUsers(){

    }

    public static void removeProduct(){

    }

    public static void createDiscount() {

    }

    public static void removeDiscount(int id) {

    }

    public static void editDiscount(int id) {

    }

    public static void showDiscounts() {

    }

}
