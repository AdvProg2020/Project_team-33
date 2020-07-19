package Client.View.LoginAndRegister;

import Client.View.BuyerMenu.BuyerMenu;
import Client.View.Menu;
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class RegisterMenu extends Menu {

    @Override
    public void show() {
        try {
            createStaticAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStaticAccount() throws IOException {
        URL url = new File("src/main/java/Client/view/LoginAndRegister/createAccount.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Menu.stage.setScene(new Scene(root, 1280, 660));
        Menu.stage.show();
    }

    public static void chooseRole() throws IOException {
        URL url = new File("src/main/java/view/LoginAndRegister/chooseRole.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Menu.stage.setScene(new Scene(root, 1280, 660));
        Menu.stage.show();
    }

    public static void createAccountForSeller() throws IOException {
        URL url = new File("src/main/java/view/LoginAndRegister/createAccountForSeller.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Menu.stage.setScene(new Scene(root, 1280, 660));
        Menu.stage.show();
    }

    public static void showIfCreateSuccessful(DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
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
            try {
                dataOutputStream.writeUTF("showFirstPage");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (dataInputStream.readUTF().equals("buyer")){
                    new BuyerMenu().showPersonalArea();
                }
                else {
                    Pane pane = new Pane();
                    Label label1 = new Label("Request Sent");
                    pane.getChildren().add(label1);
                    label1.setLayoutX(50);
                    label1.setLayoutY(50);
                    Stage stage = new Stage();
                    Scene scene = new Scene(pane, 200, 200);
                    stage.setScene(scene);
                    stage.show();
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Stage stage = new Stage();
        stage.setScene(new Scene(gridPane, 300, 300));
        stage.show();
    }
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

