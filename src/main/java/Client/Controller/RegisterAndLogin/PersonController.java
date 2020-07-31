package Client.Controller.RegisterAndLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
    private static Matcher matcher;

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
        return !getMatcher((password),"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$\n").find();
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
