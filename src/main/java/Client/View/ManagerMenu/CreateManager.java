package Client.View.ManagerMenu;

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

public class CreateManager {
    private DataInputStream dataInputStream = Menu.dataInputStream;
    private DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private String token = Menu.token;

    public void show() {
        try {
            createStaticAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStaticAccount() throws IOException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #bababa");

        Label createAccount = new Label("Create Manager Account");
        createAccount.setLayoutX(530);
        createAccount.setLayoutY(70);
        createAccount.setFont(new Font(30));
        parent.getChildren().add(createAccount);

        Label usernameLabel = new Label("Username");
        usernameLabel.setLayoutX(530);
        usernameLabel.setLayoutY(120);
        usernameLabel.setFont(new Font(15));
        parent.getChildren().add(usernameLabel);

        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(530);
        usernameTextField.setLayoutY(140);
        usernameTextField.setPrefWidth(200);
        parent.getChildren().add(usernameTextField);

        Label usernameErrorLabel = new Label();
        usernameErrorLabel.setLayoutX(530);
        usernameErrorLabel.setLayoutY(165);
        parent.getChildren().add(usernameErrorLabel);

        Label name = new Label("Name");
        name.setLayoutX(530);
        name.setLayoutY(180);
        name.setFont(new Font(15));
        parent.getChildren().add(name);

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(530);
        nameTextField.setLayoutY(200);
        nameTextField.setPrefWidth(200);
        parent.getChildren().add(nameTextField);

        Label family = new Label("Family");
        family.setLayoutX(530);
        family.setLayoutY(240);
        family.setFont(new Font(15));
        parent.getChildren().add(family);

        TextField familyTextField = new TextField();
        familyTextField.setLayoutX(530);
        familyTextField.setLayoutY(260);
        familyTextField.setPrefWidth(200);
        parent.getChildren().add(familyTextField);

        Label email = new Label("Email");
        email.setLayoutX(530);
        email.setLayoutY(310);
        email.setFont(new Font(15));
        parent.getChildren().add(email);

        TextField emailTextField = new TextField();
        emailTextField.setLayoutX(530);
        emailTextField.setLayoutY(330);
        emailTextField.setPrefWidth(200);
        parent.getChildren().add(emailTextField);

        Label emailErrorLabel = new Label();
        emailErrorLabel.setLayoutX(530);
        emailErrorLabel.setLayoutY(355);
        parent.getChildren().add(emailErrorLabel);

        Label phone = new Label("Phone");
        phone.setLayoutX(530);
        phone.setLayoutY(370);
        phone.setFont(new Font(15));
        parent.getChildren().add(phone);

        TextField phoneTextField = new TextField();
        phoneTextField.setLayoutX(530);
        phoneTextField.setLayoutY(390);
        phoneTextField.setPrefWidth(200);
        parent.getChildren().add(phoneTextField);

        Label phoneErrorLabel = new Label();
        phoneErrorLabel.setLayoutX(530);
        phoneErrorLabel.setLayoutY(415);
        parent.getChildren().add(phoneErrorLabel);

        Label password = new Label("Password");
        password.setLayoutX(530);
        password.setLayoutY(430);
        password.setFont(new Font(15));
        parent.getChildren().add(password);

        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(530);
        passwordTextField.setLayoutY(450);
        passwordTextField.setPrefWidth(200);
        parent.getChildren().add(passwordTextField);

        Label passwordErrorLabel = new Label();
        passwordErrorLabel.setLayoutX(530);
        passwordErrorLabel.setLayoutY(470);
        parent.getChildren().add(passwordErrorLabel);

        Label reEnterPassword = new Label("re-enter Password");
        reEnterPassword.setLayoutX(530);
        reEnterPassword.setLayoutY(490);
        reEnterPassword.setFont(new Font(15));
        parent.getChildren().add(reEnterPassword);

        PasswordField reEnterPasswordTextField = new PasswordField();
        reEnterPasswordTextField.setLayoutX(530);
        reEnterPasswordTextField.setLayoutY(510);
        reEnterPasswordTextField.setPrefWidth(200);
        parent.getChildren().add(reEnterPasswordTextField);

        Label reEnterPasswordErrorLabel = new Label();
        reEnterPasswordErrorLabel.setLayoutX(530);
        reEnterPasswordErrorLabel.setLayoutY(533);
        parent.getChildren().add(reEnterPasswordErrorLabel);

        Button button = new Button("Create account");
        button.setStyle("-fx-background-color: #858585");
        button.setLayoutX(530);
        button.setLayoutY(550);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(e -> {
            //TODO
            try {
                dataOutputStream.writeUTF("createManager," + usernameTextField.getText() + "," + passwordTextField.getText() + ","
                        + emailTextField.getText() + "," + phoneTextField.getText() + "," + reEnterPasswordTextField.getText() +
                        "," + nameTextField.getText() + "," + familyTextField.getText() + "," + "manager," + token);
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
                usernameErrorLabel.setText("please complete");
            }
            if (splitInput[1].equals("1")) {
                emailErrorLabel.setTextFill(Color.RED);
                emailErrorLabel.setText("please complete");
            }
            if (splitInput[2].equals("1")) {
                phoneErrorLabel.setTextFill(Color.RED);
                phoneErrorLabel.setText("please complete");
            }
            if (splitInput[3].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("please complete");
            }
            if (splitInput[4].equals("1")) {
                reEnterPasswordErrorLabel.setTextFill(Color.RED);
                reEnterPasswordErrorLabel.setText("please complete");
            }
            if (splitInput[5].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("not same");
            }
            if (splitInput[6].equals("1")) {
                usernameErrorLabel.setTextFill(Color.RED);
                usernameErrorLabel.setText("user exist with username");
            }
            if (splitInput[7].equals("1")) {
                emailErrorLabel.setTextFill(Color.RED);
                emailErrorLabel.setText("not correct");
            }
            if (splitInput[8].equals("1")) {
                phoneErrorLabel.setTextFill(Color.RED);
                phoneErrorLabel.setText("not correct");
            }
            if (splitInput[9].equals("1")) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("at least 6 characters");
            }
            if (splitInput[10].equals("pass")) {
                checkAccount();
            }
        });
        parent.getChildren().add(button);

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
        parent.getChildren().add(back);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    public static void checkAccount() {
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
                new ManagerMenu().show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Stage stage = new Stage();
        stage.setScene(new Scene(gridPane, 300, 300));
        stage.show();
    }

}
