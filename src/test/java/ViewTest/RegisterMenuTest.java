package ViewTest;

import View.RegisterMenu;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenuTest {
    private RegisterMenu registerMenu = new RegisterMenu();

    @Test
    public void simpleTest() {
        registerMenu.createAccountProcess(getMatcher("create account seller amk_amir"
                , "create account seller (\\S+)"));


    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
