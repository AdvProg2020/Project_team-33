package Controller;

import Model.Person;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class createAccountForManager {
    public TextField username;
    public TextField name;
    public TextField family;
    public TextField emil;
    public PasswordField password;
    public PasswordField reenterPassword;
    public Label usernameError;
    public Label emailError;
    public Label passwordError;
    public Label reenterPasswordError;
    public TextField phone;
    public Label phoneError;

    public void registerManagerAccountProcess(MouseEvent mouseEvent) {
        boolean create = true;
        if (!PersonController.usernameTypeErr(username.getText())) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("only use letters,numbers,underline");
            create = false;
        }
        if (username.getText().isEmpty()) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("complete this field");
            create = false;
        }
        if (!PersonController.emailTypeErr(emil.getText())) {
            emailError.setTextFill(Color.RED);
            emailError.setText("only use example@example.com");
            create = false;
        }
        if (emil.getText().isEmpty()) {
            emailError.setTextFill(Color.RED);
            emailError.setText("complete this field");
            create = false;
        }
        if (!PersonController.checkLengthOfPassWord(password.getText())) {
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
        if (!PersonController.emailTypeErr(phone.getText())) {
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
            PersonController.mainManager = RegisterProcess.createAccountForMainManager(name.getText(), family.getText(), username.getText(),
                    password.getText(), phone.getText(), emil.getText());
            PersonController.isManagerAccountCreate = true;

        }
    }
}
