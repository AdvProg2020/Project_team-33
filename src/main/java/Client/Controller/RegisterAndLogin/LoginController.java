package Client.Controller.RegisterAndLogin;

import Client.View.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoginController {
    public TextField username;
    public Label usernameError;
    public PasswordField password;
    public Label passwordError;
    public DataOutputStream dataOutputStream = Menu.dataOutputStream;
    public DataInputStream dataInputStream = Menu.dataInputStream;

    public void loginProcess(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("login," + (username.getText().isEmpty() ? " " : username.getText()) + ","
                + (password.getText().isEmpty() ? " " : password.getText()));
        dataOutputStream.flush();
        String[] splitInput = dataInputStream.readUTF().split("-");

        if (splitInput[0].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("only use letters,numbers,underline");
        }
        if (splitInput[1].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("No user with this username");
        }
        if (splitInput[2].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("complete this field");
        }
        if (splitInput[3].equals("1")) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("complete this field");
        }
        if (splitInput[4].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("Password incorrect");
        }

        if (splitInput[5].equals("pass")) {
            dataOutputStream.writeUTF("getToken");
            dataOutputStream.flush();
            System.out.println("1");
            Menu.token = dataInputStream.readUTF();
            System.out.println("h2");
            System.out.println("token: " + Menu.token);
            switch (splitInput[6]) {
                case "seller":
                    if (splitInput[7].equals("accept")) {
                        new SellerMenu().show();
                    } else if (splitInput[7].equals("unknown")) {
                        Pane pane = new Pane();
                        Label label = new Label("Request sent for manager please wait");
                        label.setFont(new Font(20));
                        label.setTextFill(Color.BLACK);
                        pane.getChildren().add(label);
                        Scene scene = new Scene(pane, 600, 400);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "buyer":
                    new BuyerMenu().show();
                    break;
                case "supporter":
                    new SupporterMenu().show();
                    break;
                case "manager":
                    new ManagerMenu().show();
                    break;
            }
        }
    }

    public void createAccountProcess(MouseEvent mouseEvent) throws IOException {
        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.createStaticAccount();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Menu.executeMainMenu();
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }
}
