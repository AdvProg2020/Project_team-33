package Controller;

import Model.Buyer;
import Model.Manager;
import Model.Person;
import Model.Seller;
import View.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
    public static Person currentPerson;
    private Person person;
    private Seller seller;
    private Buyer buyer;
    private Manager manager;
    private static Matcher matcher;

    public static void editPersonalInfo(Person person, String field, String newChange) {
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

    public static void deleteUsers() {

    }

    public static void removeProduct() {

    }

    public static void createDiscount() {

    }

    public static void removeDiscount(int id) {

    }

    public static void editDiscount(int id) {

    }

    public static void showDiscounts() {

    }

    public static boolean usernameTypeErr(String username) {
        return (matcher = getMatcher(username, "^[A-Za-z0-9_]+$")).find();
    }

    public static boolean emailTypeErr(String email) {
        return (matcher = getMatcher(email, "^\\S+@\\S+.com$")).find();
    }

    public static boolean phoneTypeErr(String phone) {
        return (matcher = getMatcher(phone, "^09\\d{9}$")).find();
    }

    public static boolean companyTypeErr(String company) {
        return (matcher = getMatcher(company, "")).find();
    }

    public static boolean existUsername(String username) {
        return Person.isAccountWithThisUsernameExist(username);
    }

    public static boolean nameTypeErr(String name) {
        return (matcher = getMatcher(name, "^[A-za-z]+$")).find();
    }

    public static boolean checkLengthOfPassWord(String password) {
        if (password.length() < 8) return false;
        return true;
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


}
