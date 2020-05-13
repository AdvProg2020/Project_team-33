package View;

import Controller.RegisterProcess;
import Model.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu extends Menu {
    public static Person currentPerson;
    private Person person;
    private static Matcher matcher;

    public LoginMenu(Menu parentMenu) {
        super("Login Menu", parentMenu);
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("login [username]");
        System.out.println("Back");
        System.out.println("Exit");
        commandProcess();
    }

    public void commandProcess() {
        String command;
        while (true) {
            command = Menu.scanner.nextLine();
            if ((matcher = getMatcher(command, "(login )(\\S)")).find()) {
                loginProcess(matcher);
            } else if (command.equalsIgnoreCase("exit")) {

            } else if (command.equalsIgnoreCase("back")) {

            } else {
                System.out.println("invalid command");
            }
        }
    }

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

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
