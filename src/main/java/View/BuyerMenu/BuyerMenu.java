package View.BuyerMenu;

import Controller.BuyerController.BuyerAbilitiesController;
import Controller.PersonController;
import Model.Users.Person;
import View.Cart;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;


public class BuyerMenu extends Menu {

    @Override
    public void show() {
        showPersonalArea();
    }

    public void showPersonalArea() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        parent.getChildren().add(label);
        makePersonalInfoPage(parent);
        makeYourOrdersPage(parent);
        makeGiftCardsPage(parent);
        balancePage(parent);
        makeTopMenu(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void makePersonalInfoPage(Pane parent) {
        Pane personalInfo = new Pane();
        personalInfo.setStyle("-fx-background-color: #bababa");
        personalInfo.setPrefWidth(210);
        personalInfo.setPrefHeight(70);
        personalInfo.setLayoutX(90);
        personalInfo.setLayoutY(200);
        personalInfo.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/personalInfo.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        personalInfo.getChildren().add(imageView);
        parent.getChildren().add(personalInfo);

        Label personalInfoLabel = new Label("Personal Info");
        personalInfoLabel.setFont(new Font(20));
        personalInfoLabel.setLayoutX(60);
        personalInfoLabel.setLayoutY(10);
        personalInfo.getChildren().add(personalInfoLabel);

        Label personalInfoSecondLabel = new Label("name,family,password,...");
        personalInfoSecondLabel.setFont(new Font(12));
        personalInfoSecondLabel.setLayoutX(60);
        personalInfoSecondLabel.setLayoutY(40);
        personalInfo.getChildren().add(personalInfoSecondLabel);
        personalInfo.setOnMouseClicked(e -> {
            BuyerPersonalInfo.editPersonalInfo();
        });

    }

    private void makeYourOrdersPage(Pane parent) {
        Pane order = new Pane();
        order.setStyle("-fx-background-color: #bababa");
        order.setPrefWidth(240);
        order.setPrefHeight(70);
        order.setLayoutX(490);
        order.setLayoutY(200);
        order.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/basket.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        order.getChildren().add(imageView);


        Label orderLabel = new Label("Your Orders");
        orderLabel.setFont(new Font(20));
        orderLabel.setLayoutX(60);
        orderLabel.setLayoutY(10);
        order.getChildren().add(orderLabel);

        Label orderSecondLabel = new Label("Track, return, or buy things again");
        orderSecondLabel.setFont(new Font(12));
        orderSecondLabel.setLayoutX(60);
        orderSecondLabel.setLayoutY(40);
        order.getChildren().add(orderSecondLabel);

        order.setOnMouseClicked(e -> BuyerBuyLogs.show());
        parent.getChildren().add(order);
    }

    private void makeGiftCardsPage(Pane parent) {
        Pane giftCard = new Pane();
        giftCard.setStyle("-fx-background-color: #bababa");
        giftCard.setPrefWidth(240);
        giftCard.setPrefHeight(70);
        giftCard.setLayoutX(890);
        giftCard.setLayoutY(200);
        giftCard.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/giftCard.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        giftCard.getChildren().add(imageView);


        Label giftCardLabel = new Label("Gift Cards");
        giftCardLabel.setFont(new Font(20));
        giftCardLabel.setLayoutX(60);
        giftCardLabel.setLayoutY(10);
        giftCard.getChildren().add(giftCardLabel);

        Label giftCardSecondLabel = new Label("Redeem a card");
        giftCardSecondLabel.setFont(new Font(12));
        giftCardSecondLabel.setLayoutX(60);
        giftCardSecondLabel.setLayoutY(40);
        giftCard.getChildren().add(giftCardSecondLabel);

        giftCard.setOnMouseClicked(e -> BuyerGiftCards.showPage());
        parent.getChildren().add(giftCard);
    }

    private void balancePage(Pane parent) {
        Pane balance = new Pane();
        balance.setStyle("-fx-background-color: #bababa");
        balance.setPrefWidth(210);
        balance.setPrefHeight(70);
        balance.setLayoutX(90);
        balance.setLayoutY(350);
        balance.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/balance.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        balance.getChildren().add(imageView);


        Label balanceLabel = new Label("Balance");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        balance.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("View Your Balance");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        balance.getChildren().add(balanceSecondLabel);

        balance.setOnMouseClicked(e -> BuyerBalance.show());
        parent.getChildren().add(balance);
    }

    private void makeTopMenu(Pane parent) {
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

        Label role = new Label("Buyer");
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
            Cart.show();

        });
        topMenu.getChildren().add(cartImage);


        parent.getChildren().add(topMenu);
    }

    static class BuyerPersonalInfo {
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
                new BuyerMenu().showPersonalArea();
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
                    BuyerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
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
                    BuyerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
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
                        BuyerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
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
                        BuyerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
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
                        BuyerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        password.setText("Password:" + "\n" + textField.getText());
                    }
                }
            });
        }
    }

    static class BuyerBalance {
        public static void show() {
            Pane parent = new Pane();
            Label balance = new Label("Balance");
            balance.setLayoutX(600);
            balance.setLayoutY(200);
            balance.setFont(new Font(25));
            parent.getChildren().add(balance);

            Button increase = new Button("Increase");
            increase.setLayoutX(580);
            increase.setLayoutY(350);
            increase.setCursor(Cursor.HAND);
            parent.getChildren().add(increase);

            Button back = new Button("Back");
            back.setLayoutX(650);
            back.setLayoutY(350);
            back.setCursor(Cursor.HAND);
            back.setOnMouseClicked(e -> new BuyerMenu().showPersonalArea());
            parent.getChildren().add(back);


            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }
    }

    static class BuyerGiftCards {
        public static void showPage() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Gift Codes");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopMenu(parent);
            showFields(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> new BuyerMenu().showPersonalArea());
            parent.getChildren().add(backButton);

            Button updateList = new Button("Update list");
            updateList.setLayoutX(400);
            updateList.setLayoutY(110);
            updateList.setStyle("-fx-background-color: #bababa");
            updateList.setCursor(Cursor.HAND);
            updateList.setOnMouseClicked(e -> {

            });
            parent.getChildren().add(updateList);

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

            Label role = new Label(" Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }

        private static void showFields(Pane parent) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(600);
            pane.setLayoutX(5);
            pane.setLayoutY(150);
            parent.getChildren().add(pane);

            Label username = new Label("Serial");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Date of start");
            name.setFont(new Font(20));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Date of end");
            family.setFont(new Font(20));
            family.setLayoutX(500);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Percent");
            phone.setFont(new Font(20));
            phone.setLayoutX(700);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            Label email = new Label("Edit");
            email.setFont(new Font(20));
            email.setLayoutX(900);
            email.setLayoutY(5);
            pane.getChildren().add(email);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(1100);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);
        }

        private static void updateList() {

        }
    }

    static class BuyerBuyLogs {

        public static void show() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Buy logs");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopOfMenu(parent);
            showFields(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> new BuyerMenu().showPersonalArea());
            parent.getChildren().add(backButton);


            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void showFields(Pane parent) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(500);
            pane.setLayoutX(5);
            pane.setLayoutY(150);
            Label productName = new Label("Serial");
            productName.setLayoutX(50);
            productName.setLayoutY(50);
            productName.setFont(new Font(25));
            pane.getChildren().add(productName);

            Label productPrice = new Label("Price");
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(50);
            productPrice.setFont(new Font(25));
            pane.getChildren().add(productPrice);

            Label numberOfProduct = new Label("Date");
            numberOfProduct.setLayoutX(550);
            numberOfProduct.setLayoutY(50);
            numberOfProduct.setFont(new Font(25));
            pane.getChildren().add(numberOfProduct);

            Label increaseOrDecrease = new Label("Off");
            increaseOrDecrease.setLayoutX(750);
            increaseOrDecrease.setLayoutY(50);
            increaseOrDecrease.setFont(new Font(25));
            pane.getChildren().add(increaseOrDecrease);

            Label finalPrice = new Label("Final Price");
            finalPrice.setLayoutX(950);
            finalPrice.setLayoutY(50);
            finalPrice.setFont(new Font(25));
            pane.getChildren().add(finalPrice);

            Label delivery = new Label("Delivery");
            delivery.setLayoutX(1150);
            delivery.setLayoutY(50);
            delivery.setFont(new Font(25));
            pane.getChildren().add(delivery);

            parent.getChildren().add(pane);
        }

        private static void makeTopOfMenu(Pane parent) {
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

            Label role = new Label(" Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }

    }

}