package Controller;

import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterProcess {

    public static Manager createAccountForMainManager(String username, String name, String family, String phone,
                                                      String email, String password) {
        return new Manager(username, name, family, phone, email, password);
    }

    public static Manager createAccountForManager(String username, String name, String family, String phone,
                                                  String email, String password) {
        return new Manager(username, name, family, phone, email, password);
    }

    public static Seller createAccountForSeller(String username, String name, String family, String phone,
                                                String email, String password, String company) {
        return new Seller(username, name, family, phone, email, password, company);
    }

    public static Buyer createAccountForBuyer(String username, String name, String family, String phone,
                                              String email, String password) {
        return new Buyer(username, name, family, phone, email, password);
    }

}
