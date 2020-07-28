package Server.Controller.RegisterAndLogin;

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

    public static boolean existUsername(String username) {
        return Person.isAccountWithThisUsernameExist(username);
    }

    public static void sendAddSellerRequestToManager(Seller seller) {
        new RequestAddSeller("Add seller", "Unknown", seller);
    }

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

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
