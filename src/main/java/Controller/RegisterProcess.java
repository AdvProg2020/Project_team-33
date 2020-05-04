package Controller;

import Model.*;

public class RegisterProcess {
    private static Seller seller;
    private static Buyer buyer;
    private static Cart cart;
    private static Manager manager;
    private static Person person;
    private static long money;
    private static String company;

    public static void createAccountForSeller(String name, String family, String username, String password,
                                              String phone, String email) {
        seller = new Seller(name, family, username, password, phone, email);
    }

    public static void createAccountForBuyer(String name, String family, String username, String password,
                                             String phone, String email) {
        cart = new Cart();
        buyer = new Buyer(name, family, username, password, phone, email, cart);
    }

}
