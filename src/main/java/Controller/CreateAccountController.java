package Controller;

import Model.Users.Person;
import View.*;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class CreateAccountController {
    public TextField username;
    public TextField name;
    public TextField family;
    public TextField email;
    public PasswordField password;
    public PasswordField reenterPassword;
    public Label usernameError;
    public Label emailError;
    public Label passwordError;
    public Label reenterPasswordError;
    public TextField phone;
    public Label phoneError;
    public TextField company;
    private static Person registeringPerson;

    public void registerAccountProcess(MouseEvent mouseEvent) throws IOException {
        boolean create = true;
        if (!PersonController.usernameTypeErr(username.getText())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("only use letters,numbers,underline");
            create = false;
        }
        if (PersonController.existUsername(username.getText())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("Username already exist");
            create = false;
        }
        if (username.getText().isEmpty()) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("complete this field");
            create = false;
        }
        if (!PersonController.emailTypeErr(email.getText())) {
            emailError.setTextFill(Color.RED);
            emailError.setText("only use example@example.com");
            create = false;
        }
        if (email.getText().isEmpty()) {
            emailError.setTextFill(Color.RED);
            emailError.setText("complete this field");
            create = false;
        }
        if (PersonController.checkLengthOfPassWord(password.getText())) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("At least 6 characters");
            create = false;
        }
        if (!reenterPassword.getText().equals(password.getText())) {
            reenterPasswordError.setTextFill(Color.RED);
            reenterPasswordError.setText("Not Same");
            create = false;
        }
        if (password.getText().isEmpty()) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("complete this field");
            create = false;
        }
        if (reenterPassword.getText().isEmpty()) {
            reenterPasswordError.setTextFill(Color.RED);
            reenterPasswordError.setText("complete this field");
            create = false;
        }
        if (!PersonController.phoneTypeErr(phone.getText())) {
            phoneError.setTextFill(Color.RED);
            phoneError.setText("wtf why wrong?!");
            create = false;
        }
        if (phone.getText().isEmpty()) {
            phoneError.setTextFill(Color.RED);
            phoneError.setText("complete this field");
            create = false;
        }
        if (create) {
            if (!PersonController.isManagerAccountCreate) {
                PersonController.mainManager = RegisterProcess.createAccountForMainManager(username.getText(), name.getText(), family.getText(),
                        phone.getText(), email.getText(), password.getText());
                LoginMenu.currentPerson = PersonController.mainManager;
                PersonController.isManagerAccountCreate = true;
                Menu.currentMenu = Menu.previousMenu;
                Menu.executeMainMenu();
            } else {
                registeringPerson = new Person(username.getText(), name.getText(), family.getText(),
                        phone.getText(), email.getText(), password.getText());
                RegisterMenu.chooseRole();
            }
        }
    }

    public void continueAsManager(MouseEvent mouseEvent) {
        Person.deleteUser(registeringPerson.getUsername());
        LoginMenu.currentPerson = RegisterProcess.createAccountForManager(registeringPerson.getUsername(),
                registeringPerson.getName(), registeringPerson.getFamily(), registeringPerson.getPhone(),
                registeringPerson.getEmail(), registeringPerson.getPassword());
        RegisterMenu.showIfCreateSuccessful();
    }

    public void continueAsBuyer(MouseEvent mouseEvent) {
        Person.deleteUser(registeringPerson.getUsername());
        LoginMenu.currentPerson = RegisterProcess.createAccountForBuyer(registeringPerson.getUsername(), registeringPerson.getName(), registeringPerson.getFamily(), registeringPerson.getPhone(), registeringPerson.getEmail(), registeringPerson.getPassword());
        RegisterMenu.showIfCreateSuccessful();
    }

    public void continueAsSeller(MouseEvent mouseEvent) throws IOException {
        RegisterMenu.createAccountForSeller();
    }

    public void createAccountForSeller(MouseEvent mouseEvent) {
        Person.deleteUser(registeringPerson.getUsername());
        PersonController.sendAddSellerRequestToManager(RegisterProcess.createAccountForSeller(registeringPerson.getUsername(), registeringPerson.getName(), registeringPerson.getFamily(), registeringPerson.getPhone(), registeringPerson.getEmail(), registeringPerson.getPassword(), company.getText()));
        RegisterMenu.showIfCreateSuccessful();
    }

}
