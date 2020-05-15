package Controller;

import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProcess {

    public static Seller createAccountForSeller(String name, String family, String username, String password,
                                                String phone, String email, String description) {
        Seller seller = new Seller(name, family, username, password, phone, email, description);
        return seller;
    }

    public static Buyer createAccountForBuyer(String name, String family, String username, String password,
                                              String phone, String email,long money) {
        Cart cart = new Cart();
        Buyer buyer = new Buyer(name, family, username, password, phone, email, cart,money);
        return buyer;
    }

    public static Person login(String username, String password) {
        if (Person.getPersonByUsername(username).getPassword().equals(password)) {
            return Person.getPersonByUsername(username);
        } else {
            return null;
        }
    }

    public static Manager createAccountForManager(String name, String family, String username, String password,
                                                  String phone, String email) {
        Manager manager = new Manager(name, family, username, password, phone, email);
        return manager;
    }


    public static boolean passwordTypeErr(String password) {
        return getMatcher(password, "^[A-Za-z0-9]+$").find();
    }

    public static boolean checkPasswordUseNumberAndAlphabet(String password) {
        if (getMatcher(password, "[0-9]").find())
            if (getMatcher(password, "[a-zA-Z]").find()) return true;
        return false;
    }

    public static boolean checkLengthOfPassWord(String password) {
        if (password.length() < 8) return false;
        return true;
    }

    public static boolean usernameTypeErr(String username) {
        return getMatcher(username, "^[A-Za-z0-9_]+$").find();
    }

    public static boolean emailTypeErr(String email) {
        return getMatcher(email, "^\\S+@\\S+.com$").find();
    }

    public static boolean phoneTypeErr(String phone) {
        return getMatcher(phone, "^09\\d{9}$").find();
    }

    public static boolean existUsername(String username) {
        return Person.isAccountWithThisUsernameExist(username);
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
