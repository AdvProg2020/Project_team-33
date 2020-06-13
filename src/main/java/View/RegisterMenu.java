package View;

import Controller.PersonController;
import Controller.RegisterProcess;
import Model.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu {

    public static void createStaticAccount(Stage stage, Scene scene) throws IOException {
        URL url = new File("src/main/java/view/createAccount.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene = new Scene(root, 1280, 660);
        stage.setScene(scene);
        stage.show();
    }

    public static void chooseRole(Stage stage) throws IOException {
        URL url = new File("src/main/java/view/chooseRole.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 1280, 660));
        stage.show();
    }


//
//    private static int registerManagerAccountCounter = 0;
//
//    public RegisterMenu() {
////        super("Register Menu");
//    }
//
//    public void help() {
//        System.out.println("Enter your command:");
//        System.out.println("create account [type] [username]");
//        System.out.println("Back");
//        System.out.println("Exit");
//        commandProcess();
//    }
//
//    public void commandProcess() {
//        String command;
//        while (true) {
//            command = Menu.scanner.nextLine();
//            Matcher matcher;
//            if ((matcher = getMatcher(command)).find()) {
//                createAccountProcess(matcher);
//            } else if (command.equalsIgnoreCase("help")) {
//                System.out.println("Enter your command:");
//                System.out.println("create account [type] [username]");
//                System.out.println("Back");
//                System.out.println("Exit");
//            } else if (command.equalsIgnoreCase("Exit")) {
//                System.exit(1);
//            } else if (command.equalsIgnoreCase("back")) {
////                Menu.show();
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void createAccountProcess(Matcher matcher) {
//        boolean passwordBoolean, usernameBoolean, nameBoolean, familyBoolean, phoneBoolean, emailBoolean;
//        String password, username, name, family, phone, email;
//        username = matcher.group(2);
//        System.out.println("password: ");
//        password = Menu.scanner.nextLine();
//        System.out.println("name: ");
//        name = Menu.scanner.nextLine();
//        System.out.println("family: ");
//        family = Menu.scanner.nextLine();
//        System.out.println("phone: ");
//        phone = Menu.scanner.nextLine();
//        System.out.println("email: ");
//        email = Menu.scanner.nextLine();
//        if (!matcher.group(1).equals("manager")) {
//            if (!PersonController.existUsername(username)) {
//                if (getPasswordOfAccount(password)) {
//                    if (getPhoneOfAccount(phone)) {
//                        if (getEmailOfAccount(email)) {
//                            if (matcher.group(1).equals("seller")) {
//                                System.out.println("description: ");
//                                String description = Menu.scanner.nextLine();
//                                System.out.println("company name:");
//                                String company = Menu.scanner.nextLine();
//                                new RequestAddSeller(username, password, phone,
//                                        name, family, email, company, description);
//                                System.out.println("your request for create seller account sent for manager");
//                                System.out.println("if your request accept you can see your user area");
//                            } else {
//                                System.out.println("money: (balance)");
//                                long money = Long.parseLong(Menu.scanner.nextLine());
//                                Buyer buyer = RegisterProcess.createAccountForBuyer(name, family, username, password,
//                                        phone, email, money);
//                                LoginMenu.currentPerson = buyer;
//                            }
//                            System.out.println("Your account successfully registered");
////                            Menu.show();
//                        }
//                    }
//                }
//            }
//        } else {
//            registerManagerAccountCounter++;
//            if (registerManagerAccountCounter == 1) {
//                if (getUsernameOfAccount(username)) {
//                    if (getPasswordOfAccount(password)) {
//                        if (getPhoneOfAccount(phone)) {
//                            if (getEmailOfAccount(email)) {
//                                System.out.println("Your account successfully registered");
////                                Menu.show();
//                            }
//                        }
//                    }
//                }
//            } else {
//                System.out.println("you cant make manager account");
//                System.out.println("only manager can made manager account");
//            }
//
//        }
//    }
//
//    protected boolean getUsernameOfAccount(String username) {
//        if (!RegisterProcess.usernameTypeErr(username)) {
//            System.out.println("Your username is not Valid.");
//            System.out.println("You can use only numbers or alphabet in your username");
//            return false;
//        }
//        if (RegisterProcess.existUsername(username)) {
//            System.out.println("This username is used before");
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean getPasswordOfAccount(String password) {
//        if (!RegisterProcess.checkLengthOfPassWord(password)) {
//            System.out.println("Your password should have at least 8 character");
//            return false;
//        }
////        if (!RegisterProcess.checkPasswordUseNumberAndAlphabet(password)) {
////            System.out.println("You should use at least one number and alphabet in your password");
////            return false;
////        }
//        if (!RegisterProcess.passwordTypeErr(password)) {
//            System.out.println("your password is invalid");
//            return false;
//        }
//        return true;
//    }
//
//    protected static boolean getPhoneOfAccount(String phone) {
//        if (!RegisterProcess.phoneTypeErr(phone)) {
//            System.out.println("Your mobile number is invalid");
//            return false;
//        }
//        return true;
//    }
//
//    protected static boolean getEmailOfAccount(String email) {
//        if (!RegisterProcess.emailTypeErr(email)) {
//            System.out.println("Your email address is not valid");
//            return false;
//        }
//        return true;
//    }
//
//    private static Matcher getMatcher(String input) {
//        Pattern pattern = Pattern.compile("create account (seller|buyer|manager) (\\S+)");
//        return pattern.matcher(input);
//    }

}
