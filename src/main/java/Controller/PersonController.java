package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
    private static Matcher matcher;
    public static boolean isManagerAccountCreate = false;
    public static Manager mainManager;

    public static boolean usernameTypeErr(String username) {
        return (matcher = getMatcher(username, "^[A-Za-z0-9_]+$")).find();
    }

    public static boolean emailTypeErr(String email) {
        return (matcher = getMatcher(email, "^\\S+@\\S+\\.com$")).find();
    }

    public static boolean checkLengthOfPassWord(String password) {
        return password.length() < 6;
    }

    public static boolean phoneTypeErr(String phone) {
        return (matcher = getMatcher(phone, "^09\\d{9}$")).find();
    }

    public static boolean existUsername(String username) {
        return Person.isAccountWithThisUsernameExist(username);
    }

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

    public static ArrayList<Person> getPeople() {
        return Person.getPeople();
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.addAll(Product.allProducts);
        return products;
    }

    public static void deleteUsers(String username) {
        Person.deleteUser(username);
    }

    public static ArrayList<SellLog> sellerSellLogs(Seller seller) {
        return seller.getLogs();
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
