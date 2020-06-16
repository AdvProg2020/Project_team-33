package View.BuyerMenu;

import Controller.PersonController;
import View.LoginMenu;
import View.Menu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Paths;

public class ShowAndEditaPersonalInfoForBuyer {
    public static void editPersonalInfo() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Login & Security");
        label.setFont(new Font(30));
        label.setLayoutX(400);
        label.setLayoutY(100);
        parent.getChildren().add(label);
        makeTopMenu(parent);
        showFields(parent);
        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeTopMenu(Pane parent) {
        Pane topMenu = new Pane();
        topMenu.setStyle("-fx-background-color: #232f3e");
        topMenu.setPrefWidth(1280);
        topMenu.setPrefHeight(100);
        topMenu.setLayoutX(0);
        topMenu.setLayoutY(0);

        Image image = new Image(Paths.get("src/main/java/view/images/mainMenu.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setLayoutY(10);
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(e -> {
            try {
                Menu.executeMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        topMenu.getChildren().add(imageView);

        Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
        ImageView logOut = new ImageView(log);
        logOut.setFitWidth(100);
        logOut.setFitHeight(80);
        logOut.setLayoutX(1170);
        logOut.setLayoutY(10);
        logOut.setCursor(Cursor.HAND);
        logOut.setOnMouseClicked(e -> {
            LoginMenu.currentPerson = null;
            try {
                Menu.executeMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        topMenu.getChildren().add(logOut);
        Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
        ImageView personImage = new ImageView(person);
        personImage.setFitWidth(70);
        personImage.setFitHeight(70);
        personImage.setLayoutX(320);
        personImage.setLayoutY(10);
        topMenu.getChildren().add(personImage);

        Label role = new Label("Seller");
        role.setFont(new Font(30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        Image cart = new Image(Paths.get("src/main/java/view/images/cart.png").toUri().toString());
        ImageView cartImage = new ImageView(cart);
        cartImage.setFitWidth(70);
        cartImage.setFitHeight(70);
        cartImage.setLayoutX(940);
        cartImage.setLayoutY(10);
        cartImage.setCursor(Cursor.HAND);
        cartImage.setOnMouseClicked(e -> {

        });
        topMenu.getChildren().add(cartImage);


        parent.getChildren().add(topMenu);
    }

    private static void showFields(Pane parent) {
        Pane personalInfo = new Pane();
        personalInfo.setStyle("-fx-background-color: #bababa");
        personalInfo.setPrefWidth(400);
        personalInfo.setPrefHeight(400);
        personalInfo.setLayoutX(400);
        personalInfo.setLayoutY(150);
        parent.getChildren().add(personalInfo);
        name(personalInfo);
        family(personalInfo);
        email(personalInfo);
        phone(personalInfo);
        password(personalInfo);

        Button button = new Button("Save and back");
        button.setPrefWidth(100);
        button.setLayoutX(150);
        button.setLayoutY(300);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);
        button.setOnMouseClicked(e -> {
            BuyerPersonalArea.showPersonalArea();
        });
    }

    private static void name(Pane personalInfo) {
        Label name = new Label("Name:" + "\n" + LoginMenu.currentPerson.getName());
        name.setFont(new Font(15));
        name.setLayoutX(20);
        personalInfo.getChildren().add(name);

        Line line = new Line();
        line.setStartX(0);
        line.setEndX(400);
        line.setStartY(50);
        line.setEndY(50);
        personalInfo.getChildren().add(line);

        Button button = new Button("Edit");
        button.setLayoutX(350);
        button.setLayoutY(10);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);

        TextField textField = new TextField();
        textField.setPromptText("New name");
        textField.setLayoutX(200);
        textField.setLayoutY(10);
        personalInfo.getChildren().add(textField);
        button.setOnMouseClicked(e -> {
            Label label = new Label();
            label.setFont(new Font(10));
            label.setLayoutX(200);
            label.setLayoutY(35);
            personalInfo.getChildren().add(label);
            if (textField.getText().isEmpty()) {
                label.setText("Complete for edit");
                label.setTextFill(Color.RED);
            } else {
                PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                label.setText("Done");
                label.setTextFill(Color.GREEN);
                name.setText("Password:" + "\n" + textField.getText());
            }
        });
    }

    private static void family(Pane personalInfo) {
        Label family = new Label("Family:" + "\n" + LoginMenu.currentPerson.getFamily());
        family.setFont(new Font(15));
        family.setLayoutX(20);
        family.setLayoutY(50);
        personalInfo.getChildren().add(family);

        Line line1 = new Line();
        line1.setStartX(0);
        line1.setEndX(400);
        line1.setStartY(100);
        line1.setEndY(100);
        personalInfo.getChildren().add(line1);

        Button button = new Button("Edit");
        button.setLayoutX(350);
        button.setLayoutY(60);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);

        TextField textField = new TextField();
        textField.setPromptText("New family");
        textField.setLayoutX(200);
        textField.setLayoutY(60);
        personalInfo.getChildren().add(textField);
        button.setOnMouseClicked(e -> {
            Label label = new Label();
            label.setFont(new Font(10));
            label.setLayoutX(200);
            label.setLayoutY(85);
            personalInfo.getChildren().add(label);
            if (textField.getText().isEmpty()) {
                label.setText("Complete for edit");
                label.setTextFill(Color.RED);
            } else {
                PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                label.setText("Done");
                label.setTextFill(Color.GREEN);
                family.setText("Password:" + "\n" + textField.getText());
            }
        });
    }

    private static void email(Pane personalInfo) {

        Label email = new Label("Email:" + "\n" + LoginMenu.currentPerson.getEmail());
        email.setFont(new Font(15));
        email.setLayoutX(20);
        email.setLayoutY(100);
        personalInfo.getChildren().add(email);

        Line line2 = new Line();
        line2.setStartX(0);
        line2.setEndX(400);
        line2.setStartY(150);
        line2.setEndY(150);
        personalInfo.getChildren().add(line2);

        Button button = new Button("Edit");
        button.setLayoutX(350);
        button.setLayoutY(110);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);

        TextField textField = new TextField();
        textField.setPromptText("New email");
        textField.setLayoutX(200);
        textField.setLayoutY(110);
        personalInfo.getChildren().add(textField);
        button.setOnMouseClicked(e -> {
            Label label = new Label();
            label.setFont(new Font(10));
            label.setLayoutX(200);
            label.setLayoutY(135);
            personalInfo.getChildren().add(label);
            if (textField.getText().isEmpty()) {
                label.setText("Complete for edit");
                label.setTextFill(Color.RED);
            } else {
                if (PersonController.checkLengthOfPassWord(textField.getText())) {
                    label.setText(":||||||");
                    label.setTextFill(Color.RED);
                } else {
                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    email.setText("Email:" + "\n" + textField.getText());
                }
            }
        });
    }

    private static void phone(Pane personalInfo) {
        Label phone = new Label("Phone:" + "\n" + LoginMenu.currentPerson.getPhone());
        phone.setFont(new Font(15));
        phone.setLayoutX(20);
        phone.setLayoutY(150);
        personalInfo.getChildren().add(phone);

        Line line3 = new Line();
        line3.setStartX(0);
        line3.setEndX(400);
        line3.setStartY(200);
        line3.setEndY(200);
        personalInfo.getChildren().add(line3);

        Button button = new Button("Edit");
        button.setLayoutX(350);
        button.setLayoutY(160);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);

        TextField textField = new TextField();
        textField.setPromptText("New phone");
        textField.setLayoutX(200);
        textField.setLayoutY(160);
        personalInfo.getChildren().add(textField);
        button.setOnMouseClicked(e -> {
            Label label = new Label();
            label.setFont(new Font(10));
            label.setLayoutX(200);
            label.setLayoutY(185);
            personalInfo.getChildren().add(label);
            if (textField.getText().isEmpty()) {
                label.setText("Complete for edit");
                label.setTextFill(Color.RED);
            } else {
                if (PersonController.checkLengthOfPassWord(textField.getText())) {
                    label.setText(":||||||");
                    label.setTextFill(Color.RED);
                } else {
                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    phone.setText("Phone:" + "\n" + textField.getText());
                }
            }
        });
    }

    private static void password(Pane personalInfo) {
        Label password = new Label("Password:" + "\n" + LoginMenu.currentPerson.getPassword());
        password.setLayoutX(20);
        password.setLayoutY(200);
        password.setFont(new Font(15));
        personalInfo.getChildren().add(password);

        Line line4 = new Line();
        line4.setStartX(0);
        line4.setEndX(400);
        line4.setStartY(250);
        line4.setEndY(250);
        personalInfo.getChildren().add(line4);

        Button button = new Button("Edit");
        button.setLayoutX(350);
        button.setLayoutY(210);
        button.setCursor(Cursor.HAND);
        personalInfo.getChildren().add(button);

        TextField textField = new TextField();
        textField.setPromptText("New password");
        textField.setLayoutX(200);
        textField.setLayoutY(210);
        personalInfo.getChildren().add(textField);
        button.setOnMouseClicked(e -> {
            Label label = new Label();
            label.setFont(new Font(10));
            label.setLayoutX(200);
            label.setLayoutY(235);
            personalInfo.getChildren().add(label);
            if (textField.getText().isEmpty()) {
                label.setText("Complete for edit");
                label.setTextFill(Color.RED);
            } else {
                if (PersonController.checkLengthOfPassWord(textField.getText())) {
                    label.setText("At least 6 characters");
                    label.setTextFill(Color.RED);
                } else {
                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    password.setText("Password:" + "\n" + textField.getText());
                }
            }
        });
    }
}
