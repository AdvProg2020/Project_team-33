package View;

import Controller.RegisterProcess;
import Model.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu extends Menu {
    public static Person currentPerson;

    public LoginMenu() {
        super("Login Menu ");
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("login [username]");
        System.out.println("Help");
        System.out.println("Back");
        System.out.println("Exit");
        commandProcess();
    }

    public void commandProcess() {
        String command;
        while (true) {
            command = Menu.scanner.nextLine();
            Matcher matcher;
            if ((matcher = getMatcher(command, "(login )(\\S)")).find()) {
                loginProcess(matcher);
            } else if (command.equalsIgnoreCase("exit")) {
                System.exit(1);
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println("login [username]");
                System.out.println("Help");
                System.out.println("Back");
                System.out.println("Exit");
            } else if (command.equalsIgnoreCase("back")) {
                Menu.show();
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void loginProcess(Matcher matcher) {
        String username = matcher.group(2), password;
        System.out.println("password:");
        password = Menu.scanner.nextLine();
        if (getUsernameOfAccount(username)) {
            if (RegisterMenu.getPasswordOfAccount(password)) {
                Person person;
                if (Person.getPersonByUsername(username) != null) {
                    if ((person=RegisterProcess.login(username,password))!=null) {
                        System.out.println("login successfully");
                        currentPerson = person;
                    } else {
                        System.out.println("your password is incorrect");
                    }
                } else {
                    System.out.println("there is no user with this username");
                }
            }
        }
    }

    private boolean getUsernameOfAccount(String username) {
        if (!RegisterProcess.usernameTypeErr(username)) {
            System.out.println("Your username is not Valid.");
            System.out.println("You can use only numbers or alphabet in your username");
            return false;
        }
        return true;
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
