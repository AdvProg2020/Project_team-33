package Controller;

import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProcess {

    public static Manager createAccountForMainManager(String username, String name, String family, String phone,
                                                      String email, String password) {
        Manager manager = new Manager(username, name, family, phone, email, password);
        return manager;
    }

    public static Manager createAccountForManager(String username, String name, String family, String phone,
                                                  String email, String password) {
        Manager manager = new Manager(username, name, family, phone, email, password);
        return manager;
    }

    public static Seller createAccountForSeller(String username, String name, String family, String phone,
                                                String email, String password, String company) {
        Seller seller = new Seller(username, name, family, phone, email, password, company);
        return seller;
    }

    public static Buyer createAccountForBuyer(String username, String name, String family, String phone,
                                              String email, String password) {
        Cart cart = new Cart();
        Buyer buyer = new Buyer(username, name, family, phone, email, password);
        return buyer;
    }

}
