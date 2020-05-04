package Controller;

import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProcess {
    private static Seller seller;
    private static Buyer buyer;
    private static Cart cart;
    private static Manager manager;
    private static Person person;
    private static long money;
    private static String company;
    private static Matcher matcher;


    public static void createAccountForSeller(String name, String family, String username, String password,
                                              String phone, String email) {
        seller = new Seller(name, family, username, password, phone, email);
    }

    public static void createAccountForBuyer(String name, String family, String username, String password,
                                             String phone, String email) {
        cart = new Cart();
        buyer = new Buyer(name, family, username, password, phone, email, cart);
    }

    public static void createAccountForManager(String name, String family, String username, String password,
                                               String phone, String email) {
        manager = new Manager(name, family, username, password, phone, email);
    }

    public static boolean passwordTypeErr(String password) {
        return (matcher = getMatcher(password, "")).find();
    }

    public static boolean usernameTypeErr(String username) {
        return (matcher = getMatcher(username, "")).find();
    }

    public static boolean emailTypeErr(String email) {
        return (matcher = getMatcher(email, "")).find();
    }

    public static boolean phoneTypeErr(String phone) {
        return (matcher = getMatcher(phone, "")).find();
    }

    public static boolean companyTypeErr(String company) {
        return (matcher = getMatcher(company, "")).find();
    }

    public static boolean existUsername(String username) {
        return Person.getPersonByUsername(username) == null;
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


}
