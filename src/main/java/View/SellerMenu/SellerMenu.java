package View.SellerMenu;

import Controller.PersonController;
import Controller.SellerController.SellerAbilitiesController;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.ManagrMenu.ManagerMenu;
import View.Menu;
import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class SellerMenu extends Menu {

    //    @Override
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
        createPersonalInfoPanel(parent);
        createSellLogsPanel(parent);
        createSalesListPanel(parent);
        createBalancePanel(parent);
        createRequestPanel(parent);
        makeTopOfMenu(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void createPersonalInfoPanel(Pane parent) {
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
            SellerPersonalInfoAbilities.editPersonalInfo();
        });

    }

    private void createSellLogsPanel(Pane parent) {
        Pane sellLogs = new Pane();
        sellLogs.setStyle("-fx-background-color: #bababa");
        sellLogs.setPrefWidth(240);
        sellLogs.setPrefHeight(70);
        sellLogs.setLayoutX(490);
        sellLogs.setLayoutY(200);
        sellLogs.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/basket.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        sellLogs.getChildren().add(imageView);


        Label orderLabel = new Label("Sell Logs");
        orderLabel.setFont(new Font(20));
        orderLabel.setLayoutX(60);
        orderLabel.setLayoutY(10);
        sellLogs.getChildren().add(orderLabel);

        Label orderSecondLabel = new Label("All log of products that sell");
        orderSecondLabel.setFont(new Font(12));
        orderSecondLabel.setLayoutX(60);
        orderSecondLabel.setLayoutY(40);
        sellLogs.getChildren().add(orderSecondLabel);

        sellLogs.setOnMouseClicked(e -> SellerLogs.show());
        parent.getChildren().add(sellLogs);
    }

    private void createSalesListPanel(Pane parent) {
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


        Label giftCardLabel = new Label("Sells List");
        giftCardLabel.setFont(new Font(20));
        giftCardLabel.setLayoutX(60);
        giftCardLabel.setLayoutY(10);
        giftCard.getChildren().add(giftCardLabel);

        Label giftCardSecondLabel = new Label("List of your available products");
        giftCardSecondLabel.setFont(new Font(12));
        giftCardSecondLabel.setLayoutX(60);
        giftCardSecondLabel.setLayoutY(40);
        giftCard.getChildren().add(giftCardSecondLabel);
        parent.getChildren().add(giftCard);
    }

    private void createBalancePanel(Pane parent) {
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

        Label balanceSecondLabel = new Label("View your balance");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        balance.getChildren().add(balanceSecondLabel);

        balance.setOnMouseClicked(e -> SellerBalance.show());
        parent.getChildren().add(balance);
    }

    public void createRequestPanel(Pane parent) {
        Pane request = new Pane();
        request.setStyle("-fx-background-color: #bababa");
        request.setPrefWidth(240);
        request.setPrefHeight(70);
        request.setLayoutX(490);
        request.setLayoutY(350);
        request.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        request.getChildren().add(imageView);


        Label requestLabel = new Label("Request");
        requestLabel.setFont(new Font(20));
        requestLabel.setLayoutX(60);
        requestLabel.setLayoutY(10);
        request.getChildren().add(requestLabel);

        Label requestSecondLabel = new Label("Send request to manager");
        requestSecondLabel.setFont(new Font(12));
        requestSecondLabel.setLayoutX(60);
        requestSecondLabel.setLayoutY(40);
        request.getChildren().add(requestSecondLabel);

        request.setOnMouseClicked(e -> SellerRequests.show());
        parent.getChildren().add(request);
    }

    private void makeTopOfMenu(Pane parent) {
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


        parent.getChildren().add(topMenu);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        LoginMenu.currentPerson = new Seller("amk_amir", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321", "google");
//        show();
//    }

    static class SellerPersonalInfoAbilities {
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
            company(personalInfo);

            Button button = new Button("Save and back");
            button.setPrefWidth(100);
            button.setLayoutX(150);
            button.setLayoutY(350);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);
            button.setOnMouseClicked(e -> {
                new SellerMenu().showPersonalArea();
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
                    SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "name", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    name.setText("Name:" + "\n" + textField.getText());
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
                    SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "family", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    family.setText("Family:" + "\n" + textField.getText());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "email", textField.getText());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "phone", textField.getText());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        password.setText("Password:" + "\n" + textField.getText());
                    }
                }
            });
        }

        private static void company(Pane personalInfo) {
            Seller seller = (Seller) LoginMenu.currentPerson;
            Label company = new Label("Company:" + "\n" + seller.getCompany());
            company.setLayoutX(20);
            company.setLayoutY(250);
            company.setFont(new Font(15));
            personalInfo.getChildren().add(company);

            Line line5 = new Line();
            line5.setStartX(0);
            line5.setEndX(400);
            line5.setStartY(300);
            line5.setEndY(300);
            personalInfo.getChildren().add(line5);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(260);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New company");
            textField.setLayoutX(200);
            textField.setLayoutY(260);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(285);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
//                    seller.setCompany(textField.getText());
                    SellerAbilitiesController.editPersonalInfo(seller, "company", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    company.setText("Company:" + "\n" + textField.getText());
                }
            });
        }

    }

    static class SellerLogs {
        public static void show() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            showFields(parent);
            makeTopOfMenu(parent);
            Label label = new Label("Sell logs");
            label.setFont(new Font(30));
            label.setLayoutX(5);
            label.setLayoutY(100);
            parent.getChildren().add(label);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> new SellerMenu().showPersonalArea());
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

        private static void showFields(Pane parent) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(500);
            pane.setLayoutX(5);
            pane.setLayoutY(160);
            Label productName = new Label("Serial");
            productName.setLayoutX(10);
            productName.setLayoutY(10);
            productName.setFont(new Font(25));
            pane.getChildren().add(productName);

            Label productPrice = new Label("Price");
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(10);
            productPrice.setFont(new Font(25));
            pane.getChildren().add(productPrice);

            Label numberOfProduct = new Label("Date");
            numberOfProduct.setLayoutX(550);
            numberOfProduct.setLayoutY(10);
            numberOfProduct.setFont(new Font(25));
            pane.getChildren().add(numberOfProduct);

            Label increaseOrDecrease = new Label("Off");
            increaseOrDecrease.setLayoutX(750);
            increaseOrDecrease.setLayoutY(10);
            increaseOrDecrease.setFont(new Font(25));
            pane.getChildren().add(increaseOrDecrease);

            Label finalPrice = new Label("Final Price");
            finalPrice.setLayoutX(950);
            finalPrice.setLayoutY(10);
            finalPrice.setFont(new Font(25));
            pane.getChildren().add(finalPrice);

            Label delivery = new Label("Delivery");
            delivery.setLayoutX(1150);
            delivery.setLayoutY(10);
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

            Label role = new Label("Seller");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }


    }

    static class SellerRequests {
        public static void show() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Request Page");
            label.setLayoutX(120);
            label.setLayoutY(90);
            label.setFont(new Font(30));
            parent.getChildren().add(label);
            addProductPage(parent);
            editProductPage(parent);
            removeProductPage(parent);
            addAuctionPage(parent);
            editAuctionPage(parent);


            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void addProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label addproductLabel = new Label("Add Product");
            addproductLabel.setFont(new Font(20));
            addproductLabel.setLayoutX(45);
            addproductLabel.setLayoutY(20);
            requestPage.getChildren().add(addproductLabel);

        }

        private static void editProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(520);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label editProductLabel = new Label("Edit Product");
            editProductLabel.setFont(new Font(20));
            editProductLabel.setLayoutX(45);
            editProductLabel.setLayoutY(20);
            requestPage.getChildren().add(editProductLabel);
        }

        private static void removeProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(920);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label removeProductLabel = new Label("Remove Product");
            removeProductLabel.setFont(new Font(20));
            removeProductLabel.setLayoutX(35);
            removeProductLabel.setLayoutY(20);
            requestPage.getChildren().add(removeProductLabel);
        }

        private static void addAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(400);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label addAuctionLabel = new Label("Add Auction");
            addAuctionLabel.setFont(new Font(20));
            addAuctionLabel.setLayoutX(45);
            addAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(addAuctionLabel);
        }

        private static void editAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(520);
            requestPage.setLayoutY(400);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label editAuctionLabel = new Label("Edit Auction");
            editAuctionLabel.setFont(new Font(20));
            editAuctionLabel.setLayoutX(45);
            editAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(editAuctionLabel);
        }
    }

    static class SellerBalance {
        public static void show() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label balance = new Label("Balance");
            balance.setLayoutX(600);
            balance.setLayoutY(200);
            balance.setFont(new Font(25));
            parent.getChildren().add(balance);

//            Button increase = new Button("Increase");
//            increase.setLayoutX(580);
//            increase.setLayoutY(350);
//            increase.setCursor(Cursor.HAND);
//            increase.setOnMouseClicked(e -> {
//
//            });
//            parent.getChildren().add(increase);

            Button back = new Button("Back");
            back.setLayoutX(610);
            back.setLayoutY(350);
            back.setCursor(Cursor.HAND);
            back.setOnMouseClicked(e -> new SellerMenu().show());
            parent.getChildren().add(back);


            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }
    }
}


//    @Override
//    public void start(Stage stage) throws Exception {
//        Pane parent = new Pane();
//        parent.setStyle("-fx-background-color: #858585");
//        Label label = new Label("Your Account");
//        label.setFont(new Font(30));
//        label.setLayoutX(90);
//        label.setLayoutY(120);
//        parent.getChildren().add(label);
//        makePersonalInfoPage(parent);
//        makeYourSellLogsPage(parent);
//        salesList(parent);
//        balancePage(parent);
//        requestPage(parent);
//        makeTopMenu(parent);
//
//        Scene scene = new Scene(parent, 1280, 660);
//        Menu.stage.setScene(scene);
//        Menu.stage.show();
//    }

