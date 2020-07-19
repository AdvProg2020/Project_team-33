package Client.Controller.RegisterAndLogin;

import Server.Model.Requests.RequestAddSeller;
import Server.Model.Users.Manager;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
    private static Matcher matcher;
    public static boolean isManagerAccountCreate = false;
    public static Manager mainManager;

    public static boolean usernameTypeErr(String username) {
        return (matcher = getMatcher(username, "^[A-Za-z0-9_]+$")).find();
    }

    public static boolean phoneTypeErr(String phone) {
        return (matcher = getMatcher(phone, "^09\\d{9}$")).find();
    }

    public static boolean emailTypeErr(String email) {
        return (matcher = getMatcher(email, "^\\S+@\\S+\\.com$")).find();
    }

    public static boolean checkLengthOfPassWord(String password) {
        return password.length() < 6;
    }

    public static boolean existUsername(String username) {
        return Person.isAccountWithThisUsernameExist(username);
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static void sendAddSellerRequestToManager(Seller seller) {
        new RequestAddSeller("Add seller", "Unknown", seller);
    }


//    public static void editPersonalInfo(Person person, String field, String newChange) {
//        if (field.equalsIgnoreCase("password")) {
//            person.srtPassword(newChange);
//        } else if (field.equalsIgnoreCase("name")) {
//            person.setName(newChange);
//        } else if (field.equalsIgnoreCase("family")) {
//            person.setFamily(newChange);
//        } else if (field.equalsIgnoreCase("phone")) {
//            person.setPhone(newChange);
//        } else if (field.equalsIgnoreCase("email")) {
//            person.setEmail(newChange);
//        }
//    }

//    public static ArrayList<Person> getPeople() {
//        return Person.getPeople();
//    }

//    public static ArrayList<Product> getProducts() {
//        ArrayList<Product> products = new ArrayList<>();
//        products.addAll(Product.allProducts);
//        return products;
//    }

//    public static void deleteUsers(String username) {
//        Person.deleteUser(username);
//    }

//    public static ArrayList<SellLog> sellerSellLogs(Seller seller) {
//        return seller.getLogs();
//    }

}
