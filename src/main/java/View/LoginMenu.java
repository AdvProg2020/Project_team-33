package View;

import Controller.RegisterProcess;
import Model.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    public static Person currentPerson;
    private Person person;
    private static Matcher matcher;

    public void loginProcess(Matcher matcher) {
        String username = matcher.group(2);
        if (RegisterProcess.usernameTypeErr(username)) {
            if (Person.getPersonByUsername(username) == null) {
                System.out.println("password: ");
                String password = Menu.scanner.nextLine();
                if (RegisterProcess.passwordTypeErr(password)) {
                    if ((person = RegisterProcess.login(username, password)) != null) {
                        currentPerson = person;
                    } else {
                        System.out.println("password incorrect");
                    }
                } else {
                    System.out.println("password type incorrect");
                }
            } else {
                System.out.println("there is no user exist with this username");
            }
        } else {
            System.out.println("username type incorrect");
        }
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
        return pattern.matcher(input);
    }

}
