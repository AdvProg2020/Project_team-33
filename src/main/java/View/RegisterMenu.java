package View;

import Model.Buyer;
import Model.Seller;
import View.BuyerMenu.BuyerPersonalArea;
import View.ManagrMenu.ManagerPersonalArea;
import View.SellerMenu.SellerPersonalArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class RegisterMenu extends Menu {

    public static void createStaticAccount(Stage stage) throws IOException {
        URL url = new File("src/main/java/view/createAccount.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 1280, 660));
        stage.show();
    }

    public static void chooseRole(Stage stage) throws IOException {
        URL url = new File("src/main/java/view/chooseRole.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 1280, 660));
        stage.show();
    }

    public static void createAccountForSeller(Stage stage) throws IOException {
        URL url = new File("src/main/java/view/createAccountForSeller.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 1280, 660));
        stage.show();
    }

    public static void showIfCreateSuccessful() {
        Pane gridPane = new Pane();
        Image image = new Image(Paths.get("src/main/java/view/images/blue-plus-icon.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        gridPane.getChildren().add(imageView);
        imageView.setLayoutX(100);
        imageView.setLayoutY(100);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Button button = new Button("Click to continue");
        Label label = new Label();
        label.setText("Register Successfully");
        label.setTextFill(Color.GREEN);
        gridPane.getChildren().add(label);
        label.setLayoutX(100);
        label.setLayoutY(170);
        gridPane.getChildren().add(button);
        button.setLayoutX(100);
        button.setLayoutY(200);
        button.setOnMouseClicked(e -> {
            if (LoginMenu.currentPerson instanceof Seller) {
                SellerPersonalArea.showPersonalArea();
            } else if (LoginMenu.currentPerson instanceof Buyer) {
                BuyerPersonalArea.showPersonalArea();
            } else {
                ManagerPersonalArea.showPersonalArea();
            }
        });
        Stage stage = new Stage();
        stage.setScene(new Scene(gridPane, 300, 300));
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
