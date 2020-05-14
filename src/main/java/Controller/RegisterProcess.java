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


    public static Seller createAccountForSeller(String name, String family, String username, String password,
                                              String phone, String email , String description) {
        seller = new Seller(name, family, username, password, phone, email ,description);
        return seller;
    }

    public static void createAccountForBuyer(String name, String family, String username, String password,
                                             String phone, String email) {
        cart = new Cart();
        buyer = new Buyer(name, family, username, password, phone, email, cart);
    }

    public static Person login(String username,String password){
        if(Person.getPersonByUsername(username).getPassword().equals(password)){
            return Person.getPersonByUsername(username);
        }else{
            return null;
        }
    }

    public static void createAccountForManager(String name, String family, String username, String password,
                                               String phone, String email) {
        manager = new Manager(name, family, username, password, phone, email);
    }

    public static boolean passwordTypeErr(String password) {
        return (matcher = getMatcher(password, "^[A-Za-z0-9]+$")).find();
    }

    public static boolean checkPasswordUseNumberAndAlphabet(String password){
        if ((matcher = getMatcher(password , "[0-9]")).find())
            if ((matcher = getMatcher(password , "[a-zA-Z]")).find()) return true ;
        return false;
    }

    public static boolean checkLengthOfPassWord(String password){
        if (password.length() < 8) return false ;
        return true ;
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

    public static boolean nameTypeErr(String name){
        return (matcher = getMatcher(name , "^[A-za-z]+$")).find();
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static boolean existEmail(String email){
        return  Person.isAccountWithThisEmailExist(email);
    }

    public static boolean existPhone(String phone){
        return Person.isAccountWithThisPhoneExist(phone);
    }
}
