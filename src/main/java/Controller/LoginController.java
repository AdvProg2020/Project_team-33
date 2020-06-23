package Controller;

import Model.Users.Buyer;
import Model.Users.Person;
import Model.Users.Seller;
import View.*;
import View.BuyerMenu.BuyerPersonalArea;
import View.ManagrMenu.ManagerPersonalArea;
import View.SellerMenu.SellerPersonalArea;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        if (login) {
            if (!Person.getPersonByUsername(username.getText()).getPassword().equals(password)) {
                usernameError.setTextFill(Color.RED);
                usernameError.setText("Password incorrect");
                login = false;
            } else {
                LoginMenu.currentPerson = Person.getPersonByUsername(username.getText());
                if (LoginMenu.currentPerson instanceof Seller) {
                    SellerPersonalArea.showPersonalArea();
                } else if (LoginMenu.currentPerson instanceof Buyer) {
                    BuyerPersonalArea.showPersonalArea();
                } else {
                    ManagerPersonalArea.showPersonalArea();
                }
            }
        }
    }

    public void createAccountProcess(MouseEvent mouseEvent) throws IOException {
        RegisterMenu.createStaticAccount(Menu.stage);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Menu.executeMainMenu();
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }
}
