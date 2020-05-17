package View;

import Controller.PersonController;
import Controller.RegisterProcess;
import Model.Buyer;
import Model.Manager;
import Model.Person;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu {

    private static int registerManagerAccountCounter = 0;

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

    private void createAccountProcess(Matcher matcher) {
        String password, username, name, family, phone, email;
        username = matcher.group(2);
        System.out.println("password: ");
        password = Menu.scanner.nextLine();
        System.out.println("name: ");
        name = Menu.scanner.nextLine();
        System.out.println("family: ");
        family = Menu.scanner.nextLine();
        System.out.println("phone: ");
        phone = Menu.scanner.nextLine();
        System.out.println("email: ");
        email = Menu.scanner.nextLine();
        if (!matcher.group(1).equals("manager")) {
            if (!PersonController.existUsername(username)) {
                if (getPasswordOfAccount(password)) {
                    if (getPhoneOfAccount(phone)) {
                        if (getEmailOfAccount(email)) {
                            if (matcher.group(1).equals("seller")) {
                                System.out.println("description: ");
                                String description = Menu.scanner.nextLine();
                                System.out.println("company name:");
                                String company = Menu.scanner.nextLine();
                                Seller seller = RegisterProcess.createAccountForSeller(name, family, username,
                                        password, phone, email, description, company);
                                LoginMenu.currentPerson = seller;
                            } else {
                                System.out.println("money: (balance)");
                                long money = Long.parseLong(Menu.scanner.nextLine());
                                Buyer buyer = RegisterProcess.createAccountForBuyer(name, family, username, password,
                                        phone, email, money);
                                LoginMenu.currentPerson = buyer;
                            }
                            System.out.println("Your account successfully registered");
                            Menu.show();
                        }
                    }
                }
            }
        } else {
            registerManagerAccountCounter++;
            if (registerManagerAccountCounter == 1) {
                if (getUsernameOfAccount(username)) {
                    if (getPasswordOfAccount(password)) {
                        if (getPhoneOfAccount(phone)) {
                            if (getEmailOfAccount(email)) {
                                System.out.println("Your account successfully registered");
                                Manager manager = RegisterProcess.createAccountForManager(name, family, username, password,
                                        phone, email);
                                LoginMenu.currentPerson = manager;
                                Menu.show();
                            }
                        }
                    }
                }
            } else {
                System.out.println("you cant make manager account");
            }

        }
    }

    protected boolean getUsernameOfAccount(String username) {
        if (!RegisterProcess.usernameTypeErr(username)) {
            System.out.println("Your username is not Valid.");
            System.out.println("You can use only numbers or alphabet in your username");
            return false;
        }
        if (RegisterProcess.existUsername(username)) {
            System.out.println("This username is used before");
            return false;
        }
        return true;
    }

    public static boolean getPasswordOfAccount(String password) {
        if (!RegisterProcess.checkLengthOfPassWord(password)) {
            System.out.println("Your password should have at least 8 character");
            return false;
        }
        if (!RegisterProcess.checkPasswordUseNumberAndAlphabet(password)) {
            System.out.println("You should use at least one number and alphabet in your password");
            return false;
        }
        if (!RegisterProcess.passwordTypeErr(password)) {
            System.out.println("your password is invalid");
            return false;
        }
        return true;
    }

    protected static boolean getPhoneOfAccount(String phone) {
        if (!RegisterProcess.phoneTypeErr(phone)) {
            System.out.println("Your mobile number is invalid");
            return false;
        }
        return true;
    }

    protected static boolean getEmailOfAccount(String email) {
        if (!RegisterProcess.emailTypeErr(email)) {
            System.out.println("Your email address is not valid");
            return false;
        }
        return true;
    }

    private static Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile("create account (seller|buyer|manager) (\\S+)");
        return pattern.matcher(input);
    }

}
