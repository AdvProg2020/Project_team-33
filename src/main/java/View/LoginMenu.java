package View;

import Model.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    private Person person;
    private static Matcher matcher;

    public void loginProcess(Matcher matcher) {
        String username=matcher.group(2);
        System.out.println("password: ");
        String password=Menu.scanner.nextLine();

    }

    public void commandProcess() {

        if ((matcher = getMatcher(Menu.scanner.nextLine(), "(login )(\\S)")).find()) {
            loginProcess(matcher);
        } else {
            System.out.println("invalid command");
        }
    }

    public void help() {

    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

}
