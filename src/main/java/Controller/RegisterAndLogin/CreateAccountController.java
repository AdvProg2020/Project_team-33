package Controller.RegisterAndLogin;

import Model.Users.Person;
import Model.Users.Seller;
import View.*;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;

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
    private Socket socket;
    private DataInputStream dataInputStream = Menu.dataInputStream;
    private DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private ObjectOutputStream objectOutputStream = Menu.objectOutputStream;
    private ObjectInputStream objectInputStream = Menu.objectInputStream;

    public void registerAccountProcess(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("createAccount," + username.getText() + "," + name.getText() + "," + family.getText() +
                "," + email.getText() + "," + password.getText() + "," + reenterPassword.getText() + "," + phone.getText());
        dataOutputStream.flush();
        String status = dataInputStream.readUTF();
        String[] splitStatus = status.split("-");

        if (splitStatus[0].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("only use letters,numbers,underline");
        }
        if (splitStatus[1].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("Username already exist");
        }
        if (splitStatus[2].equals("1")) {
            usernameError.setTextFill(Color.RED);
            usernameError.setText("complete this field");
        }
        if (splitStatus[3].equals("1")) {
            emailError.setTextFill(Color.RED);
            emailError.setText("only use example@example.com");
        }
        if (splitStatus[4].equals("1")) {
            emailError.setTextFill(Color.RED);
            emailError.setText("complete this field");
        }
        if (splitStatus[5].equals("1")) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("At least 6 characters");
        }
        if (splitStatus[6].equals("1")) {
            reenterPasswordError.setTextFill(Color.RED);
            reenterPasswordError.setText("Not Same");
        }
        if (splitStatus[7].equals("1")) {
            passwordError.setTextFill(Color.RED);
            passwordError.setText("complete this field");
        }
        if (splitStatus[8].equals("1")) {
            reenterPasswordError.setTextFill(Color.RED);
            reenterPasswordError.setText("complete this field");
        }
        if (splitStatus[9].equals("1")) {
            phoneError.setTextFill(Color.RED);
            phoneError.setText("wtf why wrong?!");
        }
        if (splitStatus[10].equals("1")) {
            phoneError.setTextFill(Color.RED);
            phoneError.setText("complete this field");
        }

        if (splitStatus[11].equals("pass")) {
            if (splitStatus[12].equals("1")) {
                new ManagerMenu().show();
            } else {
                registeringPerson = (Person) objectInputStream.readObject();
                RegisterMenu.chooseRole();
            }
        }
    }

    public void continueAsBuyer(MouseEvent mouseEvent) throws IOException {
        dataOutputStream.writeUTF("chooseRole,buyer");
        dataOutputStream.flush();
        if (dataInputStream.readUTF().equals("done")) {
            RegisterMenu.showIfCreateSuccessful(dataOutputStream, dataInputStream);
        }
    }

    public void continueAsSeller(MouseEvent mouseEvent) throws IOException {
        RegisterMenu.createAccountForSeller();
    }

    public void createAccountForSeller(MouseEvent mouseEvent) throws IOException {
        dataOutputStream.writeUTF("chooseRole,seller," + company.getText());
        dataOutputStream.flush();
        if (dataInputStream.readUTF().equals("done")) {
            RegisterMenu.showIfCreateSuccessful(dataOutputStream, dataInputStream);
        }
    }

    public void backProcess(MouseEvent mouseEvent) {
        try {
            Menu.executeMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
