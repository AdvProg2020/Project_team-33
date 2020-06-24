package View.ManagrMenu;

import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Paths;

public class ShowAndEditAllMembers {
//    static class ManagerAllMembersAbilities {
//        public static void showPage() {
//            Pane parent = new Pane();
//            parent.setStyle("-fx-background-color: #858585");
//            Label label = new Label("ALl Members");
//            label.setFont(new Font(30));
//            label.setLayoutX(10);
//            label.setLayoutY(100);
//            parent.getChildren().add(label);
//            makeTopMenu(parent);
//            showFields(parent);
//
//            Button backButton = new Button("Back");
//            backButton.setLayoutX(300);
//            backButton.setLayoutY(110);
//            backButton.setStyle("-fx-background-color: #bababa");
//            backButton.setCursor(Cursor.HAND);
//            backButton.setOnMouseClicked(e -> new ManagerMenu().showPersonalArea());
//            parent.getChildren().add(backButton);
//
//            Button updateList = new Button("Update list");
//            updateList.setLayoutX(400);
//            updateList.setLayoutY(110);
//            updateList.setStyle("-fx-background-color: #bababa");
//            updateList.setCursor(Cursor.HAND);
//            updateList.setOnMouseClicked(e -> {
//
//            });
//            parent.getChildren().add(updateList);
//
//            Scene scene = new Scene(parent, 1280, 660);
//            Menu.stage.setScene(scene);
//            Menu.stage.show();
//        }
//
//        private static void makeTopMenu(Pane parent) {
//            Pane topMenu = new Pane();
//            topMenu.setStyle("-fx-background-color: #232f3e");
//            topMenu.setPrefWidth(1280);
//            topMenu.setPrefHeight(100);
//            topMenu.setLayoutX(0);
//            topMenu.setLayoutY(0);
//
//            Image image = new Image(Paths.get("src/main/java/view/images/mainMenu.png").toUri().toString());
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(70);
//            imageView.setFitHeight(70);
//            imageView.setLayoutY(10);
//            imageView.setCursor(Cursor.HAND);
//            imageView.setOnMouseClicked(e -> {
//                try {
//                    Menu.executeMainMenu();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            topMenu.getChildren().add(imageView);
//
//            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
//            ImageView logOut = new ImageView(log);
//            logOut.setFitWidth(100);
//            logOut.setFitHeight(80);
//            logOut.setLayoutX(1170);
//            logOut.setLayoutY(10);
//            logOut.setCursor(Cursor.HAND);
//            logOut.setOnMouseClicked(e -> {
//                LoginMenu.currentPerson = null;
//                try {
//                    Menu.executeMainMenu();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            topMenu.getChildren().add(logOut);
//            Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
//            ImageView personImage = new ImageView(person);
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);
//
//            Label role = new Label("Manager");
//            role.setFont(new Font(30));
//            role.setLayoutX(640);
//            role.setLayoutY(30);
//            role.setTextFill(Color.WHITE);
//            topMenu.getChildren().add(role);
//
//
//            parent.getChildren().add(topMenu);
//        }
//
//        private static void showFields(Pane parent) {
//            Pane pane = new Pane();
//            pane.setStyle("-fx-background-color: #bababa");
//            pane.setPrefWidth(1270);
//            pane.setPrefHeight(600);
//            pane.setLayoutX(5);
//            pane.setLayoutY(150);
//            parent.getChildren().add(pane);
//
//            Label username = new Label("Username");
//            username.setFont(new Font(20));
//            username.setLayoutX(10);
//            username.setLayoutY(5);
//            pane.getChildren().add(username);
//            Label name = new Label("Name");
//            name.setFont(new Font(20));
//            name.setLayoutX(300);
//            name.setLayoutY(5);
//            pane.getChildren().add(name);
//
//            Label family = new Label("Family");
//            family.setFont(new Font(20));
//            family.setLayoutX(500);
//            family.setLayoutY(5);
//            pane.getChildren().add(family);
//
//            Label phone = new Label("Phone");
//            phone.setFont(new Font(20));
//            phone.setLayoutX(700);
//            phone.setLayoutY(5);
//            pane.getChildren().add(phone);
//
//            Label email = new Label("Email");
//            email.setFont(new Font(20));
//            email.setLayoutX(900);
//            email.setLayoutY(5);
//            pane.getChildren().add(email);
//
//            Label delete = new Label("Delete");
//            delete.setFont(new Font(20));
//            delete.setLayoutX(1100);
//            delete.setLayoutY(5);
//            pane.getChildren().add(delete);
//        }
//
//        private static void updateList() {
//
//        }
//
//
//    }

}
