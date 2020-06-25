package View;

import Controller.PersonController;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class PurchaseMenu {
    public static void purchasePage() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #bababa");
        pane.setPrefWidth(400);
        pane.setPrefHeight(500);
        pane.setLayoutX(400);
        pane.setLayoutY(100);
        Label username = new Label("Username:\nAmirMahdi");
        username.setFont(new Font(17));
        pane.getChildren().add(username);
        addFields(pane);


        parent.getChildren().add(pane);
        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void addFields(Pane pane) {
        Label name = new Label("Name:");
        name.setLayoutY(50);
        pane.getChildren().add(name);
        TextField nameField = new TextField();
        nameField.setLayoutY(70);
        name.setLayoutX(20);
        nameField.setLayoutX(20);
        pane.getChildren().add(nameField);

        Label family = new Label("Family:");
        family.setLayoutY(100);
        pane.getChildren().add(family);
        TextField familyField = new TextField();
        familyField.setLayoutY(120);
        family.setLayoutX(20);
        familyField.setLayoutX(20);
        pane.getChildren().add(familyField);

        Label phone = new Label("Phone:");
        phone.setLayoutY(150);
        pane.getChildren().add(phone);
        TextField phoneField = new TextField();
        phoneField.setLayoutY(170);
        phone.setLayoutX(20);
        phoneField.setLayoutX(20);
        pane.getChildren().add(phoneField);

        Label email = new Label("Email:");
        email.setLayoutY(200);
        pane.getChildren().add(email);
        TextField emailField = new TextField();
        emailField.setLayoutY(220);
        email.setLayoutX(20);
        emailField.setLayoutX(20);
        pane.getChildren().add(emailField);

        Label address = new Label("Address:");
        address.setLayoutY(250);
        pane.getChildren().add(address);
        TextField addressField = new TextField();
        addressField.setLayoutY(270);
        addressField.setPrefWidth(300);
        addressField.setPrefHeight(100);
        address.setLayoutX(20);
        addressField.setLayoutX(20);
        pane.getChildren().add(addressField);

        Label code = new Label("Gift code:");
        code.setLayoutY(380);
        pane.getChildren().add(code);
        TextField codeField = new TextField();
        codeField.setLayoutY(400);
        code.setLayoutX(20);
        codeField.setLayoutX(20);
        pane.getChildren().add(codeField);

        Button button = new Button("Purchase");
        button.setLayoutX(150);
        button.setLayoutY(450);
        pane.getChildren().add(button);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(e -> {
            if (nameField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(70);
                pane.getChildren().add(label);
            }
            if (familyField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(120);
                pane.getChildren().add(label);
            }
            if (addressField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(330);
                label.setLayoutY(270);
                pane.getChildren().add(label);
            }
            if (phoneField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(170);
                pane.getChildren().add(label);
            } else {
                if (!PersonController.phoneTypeErr(phoneField.getText())) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(170);
                    pane.getChildren().add(label);
                }
            }
            if (emailField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(220);
                pane.getChildren().add(label);
            } else {
                if (!PersonController.emailTypeErr(emailField.getText())) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(220);
                    pane.getChildren().add(label);
                }
            }

            if (codeField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(400);
                pane.getChildren().add(label);
            }
        });
    }
}
