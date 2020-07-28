package Server.Controller.RegisterAndLogin;

import Server.Model.Users.Buyer;
import Server.Model.Users.Manager;
import Server.Model.Users.Seller;

public class RegisterProcess {

    public static Manager createAccountForMainManager(String username, String name, String family, String phone,
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
