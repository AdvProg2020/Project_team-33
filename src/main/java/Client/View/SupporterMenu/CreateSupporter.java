package Client.View.SupporterMenu;

import Client.View.ManagerMenu.CreateManager;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.Menu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class CreateSupporter {
    private DataInputStream dataInputStream = Menu.dataInputStream;
    private DataOutputStream dataOutputStream = Menu.dataOutputStream;

    public void show() {
        createSupporterAccount();
    }

    private void createSupporterAccount() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #bababa");

        Label createAccountLabel = new Label("Create Supporter Account");
        createAccountLabel.setLayoutX(530);
        createAccountLabel.setLayoutY(70);
        createAccountLabel.setFont(new Font(30));
        pane.getChildren().add(createAccountLabel);

        Label usernameLabel = new Label("Username*");
        usernameLabel.setLayoutX(530);
        usernameLabel.setLayoutY(120);
        usernameLabel.setFont(new Font(15));
        pane.getChildren().add(usernameLabel);

        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(530);
        usernameTextField.setLayoutY(140);
        usernameTextField.setPrefWidth(200);
        pane.getChildren().add(usernameTextField);

        Label usernameErrorLabel = new Label();
        usernameErrorLabel.setLayoutX(530);
        usernameErrorLabel.setLayoutY(165);
        pane.getChildren().add(usernameErrorLabel);

        Label name = new Label("Name");
        name.setLayoutX(530);
        name.setLayoutY(180);
        name.setFont(new Font(15));
        pane.getChildren().add(name);

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(530);
        nameTextField.setLayoutY(200);
        nameTextField.setPrefWidth(200);
        pane.getChildren().add(nameTextField);

        Label family = new Label("Family");
        family.setLayoutX(530);
        family.setLayoutY(240);
        family.setFont(new Font(15));
        pane.getChildren().add(family);

        TextField familyTextField = new TextField();
        familyTextField.setLayoutX(530);
        familyTextField.setLayoutY(260);
        familyTextField.setPrefWidth(200);
        pane.getChildren().add(familyTextField);

        Label email = new Label("Email*");
        email.setLayoutX(530);
        email.setLayoutY(310);
        email.setFont(new Font(15));
        pane.getChildren().add(email);

        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(530);
        emailTextField.setLayoutY(330);
        emailTextField.setPrefWidth(200);
        pane.getChildren().add(emailTextField);

        Label emailErrorLabel = new Label();
        emailErrorLabel.setLayoutX(530);
        emailErrorLabel.setLayoutY(355);
        pane.getChildren().add(emailErrorLabel);

        Label phoneNo = new Label("Phone*");
        phoneNo.setLayoutX(530);
        phoneNo.setLayoutY(370);
        phoneNo.setFont(new Font(15));
        pane.getChildren().add(phoneNo);

        TextField phoneTextField = new TextField();
        phoneTextField.setLayoutX(530);
        phoneTextField.setLayoutY(390);
        phoneTextField.setPrefWidth(200);
        pane.getChildren().add(phoneTextField);

        Label phoneErrorLabel = new Label();
        phoneErrorLabel.setLayoutX(530);
        phoneErrorLabel.setLayoutY(415);
        pane.getChildren().add(phoneErrorLabel);

        Label password = new Label("Password*");
        password.setLayoutX(530);
        password.setLayoutY(430);
        password.setFont(new Font(15));
        pane.getChildren().add(password);

        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(530);
        passwordTextField.setLayoutY(450);
        passwordTextField.setPrefWidth(200);
        pane.getChildren().add(passwordTextField);

        Label passwordErrorLabel = new Label();
        passwordErrorLabel.setLayoutX(530);
        passwordErrorLabel.setLayoutY(470);
        pane.getChildren().add(passwordErrorLabel);

        Label reEnterPassword = new Label("re-enter Password*");
        reEnterPassword.setLayoutX(530);
        reEnterPassword.setLayoutY(490);
        reEnterPassword.setFont(new Font(15));
        pane.getChildren().add(reEnterPassword);

        PasswordField reEnterPasswordTextField = new PasswordField();
        reEnterPasswordTextField.setLayoutX(530);
        reEnterPasswordTextField.setLayoutY(510);
        reEnterPasswordTextField.setPrefWidth(200);
        pane.getChildren().add(reEnterPasswordTextField);

        Label reEnterPasswordErrorLabel = new Label();
        reEnterPasswordErrorLabel.setLayoutX(530);
        reEnterPasswordErrorLabel.setLayoutY(533);
        pane.getChildren().add(reEnterPasswordErrorLabel);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #858585");
        back.setLayoutX(660);
        back.setLayoutY(550);
        back.setOnMouseClicked(e -> {
            try {
                new ManagerMenu().show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        back.setCursor(Cursor.HAND);
        pane.getChildren().add(back);

        Button createAccount = new Button("Create account");
        createAccount.setStyle("-fx-background-color: #858585");
        createAccount.setLayoutX(530);
        createAccount.setLayoutY(550);
        createAccount.setCursor(Cursor.HAND);
        createAccount.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("createManager," + usernameTextField.getText() + "," + passwordTextField.getText() + ","
                        + emailTextField.getText() + "," + phoneTextField.getText() + "," + reEnterPasswordTextField.getText() +
                        "," + nameTextField.getText() + "," + familyTextField.getText() + "," + "supporter" + "," + Menu.token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String[] splitInput = new String[11];
            try {
                splitInput = dataInputStream.readUTF().split("-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (splitInput[0].equals("1")) {
                usernameErrorLabel.setTextFill(Color.RED);
                usernameErrorLabel.setText("please complete the field");
            }
            if (splitInput[1].equals("1")) {
                emailErrorLabel.setTextFill(Color.RED);
                emailErrorLabel.setText("please complete the field");
            }
            if (splitInput[2].equals("1")) {
                phoneErrorLabel.setTextFill(Color.RED);
                phoneErrorLabel.setText("please complete the field");
            }
            if (splitInput[3].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("please complete the field");
            }
            if (splitInput[4].equals("1")) {
                reEnterPasswordErrorLabel.setTextFill(Color.RED);
                reEnterPasswordErrorLabel.setText("please complete the field");
            }
            if (splitInput[5].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("password and reEntered password are not the same");
            }
            if (splitInput[6].equals("1")) {
                usernameErrorLabel.setTextFill(Color.RED);
                usernameErrorLabel.setText("user exists with username");
            }
            if (splitInput[7].equals("1")) {
                emailErrorLabel.setTextFill(Color.RED);
                emailErrorLabel.setText("email format is not correct");
            }
            if (splitInput[8].equals("1")) {
                phoneErrorLabel.setTextFill(Color.RED);
                phoneErrorLabel.setText("phone number format is not correct");
            }
            if (splitInput[9].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("at least 6 characters");
            }
            if (splitInput[10].equals("pass")) {
                checkAccount();
            }
        });
        pane.getChildren().add(createAccount);

        Label startLabel = new Label("* : has to be completed");
        startLabel.setLayoutX(530);
        startLabel.setLayoutY(580);
        startLabel.setTextFill(Color.RED);
        pane.getChildren().add(startLabel);

        Scene scene = new Scene(pane, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void checkAccount() {
        showIfCreateSuccessful();
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
            try {
                new SupporterMenu().show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Stage stage = new Stage();
        stage.setScene(new Scene(gridPane, 300, 300));
        stage.show();
    }

}
