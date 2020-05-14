package View;

import Controller.RegisterProcess;
import Model.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu {

    public RegisterMenu() {
        super("Register Menu");
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("create account [type] [username]");
        System.out.println("Back");
        System.out.println("Exit");
        commandProcess();
    }

    public void commandProcess() {
        String command;
        while (true) {
            command = Menu.scanner.nextLine();
            Matcher matcher;
            if ((matcher = getMatcher(command)).find()) {
                createAccountProcess(matcher);
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println("Enter your command:");
                System.out.println("create account [type] [username]");
                System.out.println("Back");
                System.out.println("Exit");
            } else if (command.equalsIgnoreCase("Exit")) {
                System.exit(1);
            } else if (command.equalsIgnoreCase("back")) {
                Menu.show();
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void createAccountProcess(Matcher matcher) {
        String password, username, name, family, phone, email;
        int flag = 0;
        username = matcher.group(2);
        while (true) {
            if (flag == 1) {
                System.out.println("Username: ");
                username = Menu.scanner.nextLine();
            }
            if (getUsernameOfAccount(username)) {
                break;
            }
            flag = 1;
        }
        do {
            System.out.println("password: ");
            password = Menu.scanner.nextLine();
        } while (!getPasswordOfAccount(password));
        do {
            System.out.println("name: ");
            name = Menu.scanner.nextLine();
        } while (getFamilyOrNameOfAccount(name));
        do {
            System.out.println("family: ");
            family = Menu.scanner.nextLine();
        } while (getFamilyOrNameOfAccount(family));
        do {
            System.out.println("phone: ");
            phone = Menu.scanner.nextLine();
        } while (!getPhoneOfAccount(phone));
        do {
            System.out.println("email: ");
            email = Menu.scanner.nextLine();
        } while (!getEmailOfAccount(email));
        if (matcher.group(1).equals("seller")) {
            System.out.println("description: ");
            String description = Menu.scanner.nextLine();
            RegisterProcess.createAccountForSeller(name, family, username, password, phone, email, description);
        } else {
            RegisterProcess.createAccountForBuyer(name, family, username, password, phone, email);
        }
        System.out.println("Your account successfully registered");
        LoginMenu.currentPerson = Person.getPersonByUsername(username);
        Menu.show();
    }

    public boolean getUsernameOfAccount(String username) {
        if (!RegisterProcess.usernameTypeErr(username)) {
            System.out.println("Your username is not Valid.");
            System.out.println("You can use only numbers or alphabet in your username");
            return false;
        }
        if (RegisterProcess.existUsername(username)) {
            System.out.println("This username is used before.\\n" +
                    "Please use another userName");
            return false;
        }
        return true;
    }

    private boolean getPasswordOfAccount(String password) {
        if (!RegisterProcess.checkLengthOfPassWord(password)) {
            System.out.println("Your password should have at least 8 character");
            return false;
        }
        if (!RegisterProcess.checkPasswordUseNumberAndAlphabet(password)) {
            System.out.println("You should use at least on number and alphabet in your password");
            return false;
        }
        if (!RegisterProcess.passwordTypeErr(password)) {
            System.out.println("your password is invalid");
            return false;
        }
        return true;
    }

    private boolean getPhoneOfAccount(String phone) {
        if (!RegisterProcess.phoneTypeErr(phone)) {
            System.out.println("Your mobile number is invalid");
            return false;
        }
        if (RegisterProcess.existPhone(phone)) {
            System.out.println("This phone used before\\n" +
                    "Please use another phone number");
            return false;
        }
        return true;
    }

    private boolean getFamilyOrNameOfAccount(String name) {
        if (!RegisterProcess.nameTypeErr(name)) {
            System.out.println("Are you kidding us ? :) )");
            return true;
        }
        return false;
    }

    private boolean getEmailOfAccount(String email) {
        if (!RegisterProcess.emailTypeErr(email)) {
            System.out.println("Your email address is not valid");
            return false;
        }
        if (RegisterProcess.existEmail(email)) {
            System.out.println("This email used before\\n" +
                    "Please use another email number");
            return false;
        }
        return true;
    }

    private static Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile("create account (seller|buyer|manager) (\\S+)");
        return pattern.matcher(input);
    }

}
