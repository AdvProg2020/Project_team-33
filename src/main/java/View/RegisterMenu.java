package View;

import Controller.RegisterProcess;
import Model.Buyer;
import Model.Cart;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu {
    private Seller seller;
    private Buyer buyer;
    private Cart cart;
    private static Matcher matcher;

    public RegisterMenu() {
        super("Register Menu", null);
    }

    public void commandProcess() {
        String command ;
        while (true) {
            command = Menu.scanner.nextLine();
            if ((matcher = getMatcher(command, "create account (seller||buyer||manager) (\\S+)")).find()) {
                createAccountProcess(matcher);
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println("1-create account");
            } else if (command.equalsIgnoreCase("Exit")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void createAccountProcess(Matcher matcher) {
        String username = matcher.group(2);
        if (RegisterProcess.usernameTypeErr(username)) {
            if (RegisterProcess.existUsername(username)) {
                System.out.println("password: ");
            } else {
                return;
            }
        } else {
            return;
        }
        String password = Menu.scanner.nextLine();
        if (RegisterProcess.passwordTypeErr(password)) {
        } else {
            return;
        }
        System.out.println("name: ");
        String name = Menu.scanner.nextLine();
        System.out.println("family: ");
        String family = Menu.scanner.nextLine();
        System.out.println("phone: ");
        String phone = Menu.scanner.nextLine();
        if (RegisterProcess.phoneTypeErr(phone)) {
            System.out.println("email: ");

        } else {
            return;
        }
        String email = Menu.scanner.nextLine();
        if (RegisterProcess.emailTypeErr(email)) {
            System.out.println();
        } else {
            return;
        }
        if (matcher.group(2).equals("seller")) {
            System.out.println("description: ");
            String description = Menu.scanner.nextLine();
            RegisterProcess.createAccountForSeller(name, family, username, password, phone, email,description);
        } else {
            RegisterProcess.createAccountForBuyer(name, family, username, password, phone, email);

        }
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(input);
        return matcher ;
    }
}
