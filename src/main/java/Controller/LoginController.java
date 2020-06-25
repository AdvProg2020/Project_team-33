package Controller;

import Model.Users.Buyer;
import Model.Users.Person;
import Model.Users.Seller;
import View.*;
import View.BuyerMenu.BuyerMenu;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.SellerMenu.SellerMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField username;
    public Label usernameError;
    public PasswordField password;
    public Label passwordError;

    public void loginProcess(MouseEvent mouseEvent) {
        boolean login = true;
        if (!PersonController.usernameTypeErr(username.getText())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("only use letters,numbers,underline");
            login = false;
        }
        if (!PersonController.existUsername(username.getText())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("No user with this username");
            login = false;
        }
        if (username.getText().isEmpty()) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("complete this field");
            login = false;
        }
        if (password.getText().isEmpty()) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("complete this field");
            login = false;
        }
        if ((Person.getPersonByUsername(username.getText())!=null)&&!Person.getPersonByUsername(username.getText()).getPassword().equals(password.getText()) && (!password.getText().isEmpty())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("Password incorrect");
            login = false;
        }
        if (login) {
            LoginMenu.currentPerson = Person.getPersonByUsername(username.getText());
            if (LoginMenu.currentPerson instanceof Seller) {
                Seller seller = (Seller) LoginMenu.currentPerson;
                if (seller.getCanSellerCreate()) {
                    new SellerMenu().showPersonalArea();
                } else {
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

            } else if (LoginMenu.currentPerson instanceof Buyer) {
                new BuyerMenu().showPersonalArea();
            } else {
                new ManagerMenu().showPersonalArea();
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
